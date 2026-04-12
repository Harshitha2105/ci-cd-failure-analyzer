pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'Java-17'
    }

    environment {
        IMAGE_NAME = 'cicd-backend'
        CONTAINER_NAME = 'cicd-backend-container'
    }

    stages {

        stage('Checkout') {
            steps {
                deleteDir()
                git branch: 'master',
                url: 'https://github.com/Harshitha2105/ci-cd-failure-analyzer.git'
            }
        }

        stage('Verify Files') {
            steps {
                bat 'dir'
                bat 'dir backend'
            }
        }

        stage('Build Backend') {
            steps {
                bat 'mvn -f backend\\pom.xml clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn -f backend\\pom.xml test'
            }
        }

        stage('Verify Jar') {
            steps {
                bat 'dir backend\\target'
            }
        }

        stage('Stop Old Container') {
            steps {
                bat '''
                docker rm -f %CONTAINER_NAME% 2>nul || exit /b 0
                '''
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                docker build -t %IMAGE_NAME% backend
                '''
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                docker run -d --name %CONTAINER_NAME% -p 8081:8080 %IMAGE_NAME%
                '''
            }
        }
    }

    post {

        success {
            echo 'Pipeline executed successfully'
        }

        failure {
            echo 'Pipeline failed - check logs above'
        }

        always {
            echo 'Build process completed'
        }
    }
}
