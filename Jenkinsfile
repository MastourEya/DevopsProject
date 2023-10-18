pipeline {
    agent any
 
    stages {
        stage('Checkout') {
            steps {
                // Utilisation de Git pour récupérer le code
                git 'https://https://github.com/MastourEya/DevopsProject'
            }
        }
 
        stage('Clean compile maven') {
            steps {
                // Exécution des commandes Maven
                sh 'mvn clean compile'
            }
        }
        stage('Clean Workspace') {
            steps {
                deleteDir()
       }
}
    }
}
