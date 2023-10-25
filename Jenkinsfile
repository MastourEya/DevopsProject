pipeline {
    agent any
 
    stages {
        stage('Checkout') {
            steps {
                script {
                    def gitUrl = 'https://github.com/MastourEya/DevopsProject.git'
                    def branchName = 'master' // Spécifiez la branche que vous souhaitez
                    def gitCredentialsId = 'noreply' // Remplacez par l'ID de vos identifiants Git dans Jenkins


                    // Utilisez la commande checkout avec des paramètres
                    checkout([$class: 'GitSCM',
                        branches: [[name: branchName]],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [[$class: 'CloneOption', depth: 1, noTags: true, reference: '', shallow: true]],
                        userRemoteConfigs: [[url: gitUrl, credentialsId: gitCredentialsId]]
                    ])
                }
            }

    }
}
