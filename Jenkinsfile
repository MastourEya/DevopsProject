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
                sh 'mvn clean compile' 
            }
        }
stage('Checkout Front') {
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
                    // Remplacez ces commandes par celles nécessaires pour construire votre projet Angular
                    sh 'npm install'  // Exemple : installer les dépendances
                    sh 'ng build '  // Exemple : construire le projet Angular en mode production
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
