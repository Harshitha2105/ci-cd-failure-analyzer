pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'Java-17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Harshitha2105/ci-cd-failure-analyzer.git'
            }
        }

        stage('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test Backend') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Check Workspace') {
            steps {
                bat 'dir'
                bat 'dir frontend'
                bat 'dir backend'
            }
        }

        stage('Docker Compose Build & Run') {
            steps {
                bat 'docker-compose down'
                bat 'docker-compose build'
                bat 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully'
        }

        failure {
            echo 'Pipeline failed'
        }

        always {
            echo 'Build process completed'
        }
    }
}
