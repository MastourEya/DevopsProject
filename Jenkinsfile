pipeline {
    agent any

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
                sh 'mvn clean compile'
            }
        }

                 stage('Checkout Frontend Repo') {
                     steps {
                        script {
                            checkout([
                              $class: 'GitSCM',
                                 branches: [[name: 'master']],
                                 userRemoteConfigs: [[url: 'https://github.com/MastourEya/ProjetDevops-Angular']]
                             ])
                         }
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
                sh 'npm install -g @angular/cli'
                sh'ng update @angular/core'
                sh 'ng build'
            }
        }


        

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube-10.2.1') {
                        sh "mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=eya"
                    }
                }
            }
        }
    }
}
