pipeline {
    agent any

    stages {
        stage('Checkout') {
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

        stage('Build') {
            steps {
                sh 'mvn clean compile' // Exécute les commandes Maven clean et compile
            }
        }

stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('sonarqube') {
                        sh "mvn sonar:sonar -Dsonar.login=7f7034f62628a92d0e0fb2809cbe88744b1dc404059498cde58d9ff48f4ba6e1"
                    }
                }
            }
        }
    }

    // Définition des post-actions, notifications, etc.
}
