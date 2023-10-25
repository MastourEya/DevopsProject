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
                withSonarQubeEnv('Nom-de-votre-environnement-SonarQube') {
                    sh 'mvn sonar:sonar -Dsonar.login=eya'
                }
            }
        }
    }

    // Définition des post-actions, notifications, etc.
}
