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
                                 branches: [[name: 'main']],
                                 userRemoteConfigs: [[url: 'https://github.com/MastourEya/ProjetDevops-Angular']]
                             ])
                            sh'git stash'
                         }
                     }
                 }
         
    stage('Install Node.js') {
        sh 'curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.35.3/install.sh | bash'
        sh 'source ~/.nvm/nvm.sh'
        sh 'nvm install --lts'
        sh 'nvm use --lts'
    }
            stage('Update Angular CLI') {
        sh 'npm install -g @angular/cli'
    }

    stage('Update Project Dependencies') {
        sh 'npm update'
    }

    stage('Clear Angular CLI Cache') {
        sh 'ngcc --clear'
    }

    stage('Build Angular Project') {
        sh 'ng build'
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
