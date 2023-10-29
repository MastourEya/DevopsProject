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
                sh 'mvn clean compile'
            }
        }

        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

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
                sh 'npm install -g @angular/cli@latest' // Update Angular CLI
                sh 'npm install' // Install project dependencies
                sh 'npm run build'
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
