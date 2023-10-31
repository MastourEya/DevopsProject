pipeline {
    agent any

    environment {
        // Define the tool locations for Node.js and npm
        NODEJS_HOME = tool name: 'node'
        PATH = "${env.NODEJS_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout Main Project') {
            steps {
                script {
                    def gitUrl = 'https://github.com/MastourEya/DevopsProject.git'
                    def branchName = 'master'
                    def gitCredentialsId = 'noreply'
                    checkout([$class: 'GitSCM',
                        branches: [[name: branchName]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [[$class: 'CloneOption', depth: 1, noTags: true, reference: '', shallow: true]],
                        userRemoteConfigs: [[url: gitUrl, credentialsId: gitCredentialsId]]
                    ])
                }
            }
        }

        stage('Build Main Project') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Build and Push back Images') {
            steps {
                script {
                    // Ajoutez l'étape Git checkout pour le référentiel backend ici
                    checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/master']],
                        userRemoteConfigs: [[url: 'https://github.com/MastourEya/DevopsProject.git']]
                    ])

                    // Build the backend Docker image
                    def backendImage = docker.build('eyamastour/spring-app', '-f /var/lib/jenkins/workspace/ProjetSpring/Dockerfile .')

                    // Authentification Docker Hub avec des informations d'identification secrètes
                    withCredentials([string(credentialsId: 'docker-hub', variable: 'pwd')]) {
                        sh "docker login -u eyamastour -p ${pwd}"
                        // Poussez l'image Docker
                        backendImage.push()
                    }
                }
            }
        }

        // stage('Clean Workspace') {
        //     steps {
        //         deleteDir()
        //     }
        // }

        // stage('Checkout Frontend Repo') {
        //     steps {
        //         checkout([
        //             $class: 'GitSCM',
        //             branches: [[name: 'master']],
        //             userRemoteConfigs: [[url: 'https://github.com/MastourEya/ProjetDevops-Angular']]
        //         ])
        //     }
        // }

        // stage('Build Frontend') {
        //     steps {
        //         // Set the Node.js tool defined in Jenkins configuration
        //         script {
        //             def nodeJSHome = tool name: 'node' // Use the correct tool name
        //             env.PATH = "${nodeJSHome}/bin:${env.PATH}"
        //         }
        //         // Now you can run 'npm install' and 'ng build'
        //         sh 'npm install --legacy-peer-deps'
        //         sh 'npm run ng build'
        //     }
        // }



  

        // stage('SonarQube Analysis') {
        //     steps {
        //         script {
        //             withSonarQubeEnv('sonarqube-10.2.1') {
        //                 sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=eya"
        //             }
        //         }
        //     }
        // }
    }
}

