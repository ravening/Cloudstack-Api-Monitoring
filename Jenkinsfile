pipeline {
    agent any

    triggers {
            pollSCM('* * * * *')
    }

    environment {
        APPLICATION_NAME = 'cloudstackstats'
    }

    stages {
        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }
    }
}
