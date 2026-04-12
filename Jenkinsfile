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

        stage('Verify Backend Jar') {
            steps {
                bat 'dir backend\\target'
            }
        }

        stage('Build Frontend (Optional Check)') {
            steps {
                bat 'dir frontend'
            }
        }

        stage('Docker Compose Down') {
            steps {
                bat 'docker-compose down'
            }
        }

        stage('Docker Compose Build') {
            steps {
                bat 'docker-compose build'
            }
        }

        stage('Docker Compose Up') {
            steps {
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
