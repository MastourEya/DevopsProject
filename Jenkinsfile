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

        stage('Checkout Frontend Project') {
            steps {
                script {
                    def gitUrl = 'https://github.com/MastourEya/ProjetDevops-Angular'
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

        stage('Build Frontend') {
            steps {
                script {
                    dir('path-to-frontend-project') {
                        sh 'npm install'
                        sh 'ng build'
                    }
                }
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
