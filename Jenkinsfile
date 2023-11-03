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

        // stage('Build Main Project') {
        //     steps {
        //         sh 'mvn clean test'
        //     }
        // }
        // stage('BUILD Backend') {
        //     steps {
        //         // Use Java 8 for this stage
        //         withEnv(["JAVA_HOME=${tool name: 'JAVA_8_HOME', type: 'jdk'}"]) {
        //             sh 'mvn clean install'
        //         }
        //     }
        // }



//         stage('Run Docker Compose') {
//     steps {
//         script {
//             checkout([
//                 $class: 'GitSCM',
//                 branches: [[name: '*/master']], 
//                 userRemoteConfigs: [[url: 'https://github.com/MastourEya/DevopsProject']]
//             ])

//             // Run the docker-compose command
//             sh 'docker compose up -d' 
//         }
//     }
// }
        //    stage('COMPILE Backend') {
        //     steps {
        //         // Use the default Java 8 for this stage
        //         sh 'mvn compile'
        //     }
        // }
        
 // stage('Build and Push back Images') {
 //            steps {
 //                script {
 //                    // Checkout your Git repository for the backend code here
 //                    checkout([
 //                        $class: 'GitSCM',
 //                        branches: [[name: '*/master']],
 //                        userRemoteConfigs: [[url: 'https://github.com/MastourEya/DevopsProject.git']]
 //                    ])

 //                    // Build the backend Docker image
 //                    def backendImage = docker.build('eyamastour/spring-app', '-f /var/lib/jenkins/workspace/ProjetSpring/Dockerfile .')
 //                    // Authenticate with Docker Hub using secret credentials
 //                    withCredentials([string(credentialsId: 'docker', variable: 'pwd')]) {
 //                        sh "docker login -u eyamastour -p 123456789"
 //                            // sh "docker login -u eyamastour -p ${pwd}"
 //                        // Push the Docker image
 //                        backendImage.push()
 //                    }
 //                }
 //            }
 //        }

        // stage('Clean Workspace') {
        //     steps {
        //         deleteDir()
        //     }
        // }

        stage('Checkout Frontend Repo') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'master']],
                    userRemoteConfigs: [[url: 'https://github.com/MastourEya/ProjetDevops-Angular']]
                ])
            }
        }

        stage('Build Frontend') {
            steps {
                // Set the Node.js tool defined in Jenkins configuration
                script {
                    def nodeJSHome = tool name: 'node' // Use the correct tool name
                    env.PATH = "${nodeJSHome}/bin:${env.PATH}"
                }
                // Now you can run 'npm install' and 'ng build'
                sh 'npm install '
                sh 'npm run ng build'
            }
        }



  

        // stage('SonarQube Analysis') {
        //     steps {
        //         script {
        //             withSonarQubeEnv('sonarqube-10.2.1') {
        //                 sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=eya"
        //             }
        //         }
        //     }
        // }


stage('Build and Push front Image') {
    steps {
        script {
            // Add the Git checkout step for the frontend repository here
            checkout([
                $class: 'GitSCM',
                branches: [[name: '*/main']],
                userRemoteConfigs: [[url: 'https://github.com/MastourEya/ProjetDevops-Angular']]
            ])

            // Build the front Docker image
            def frontImage = docker.build('eyamastour/devopsbackend:front', '-f /var/lib/jenkins/workspace/ProjetSpring/Dockerfile .')

            // Authenticate with Docker Hub using secret credentials
            withCredentials([string(credentialsId: 'docker', variable: 'pwd')]) {
                sh "docker login -u eyamastour -p 12345678"
                // Push the Docker image
                frontImage.push()
            }
        }
    }
}


    }
}

