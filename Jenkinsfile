pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'Java-17'
    }

    environment {
        IMAGE_NAME = 'cicd-backend'
        CONTAINER_NAME = 'cicd-backend-container'
        API_URL = 'http://localhost:8082/api/logs/analyze'
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
                docker run -d --name %CONTAINER_NAME% -p 8082:8082 %IMAGE_NAME%
                '''
            }
        }
    }

    post {

        success {
            echo 'Pipeline executed successfully'

            bat '''
            curl -X POST %API_URL% ^
            -H "Content-Type: application/json" ^
            -d "{\\"pipelineName\\":\\"Jenkins-Pipeline\\",\\"logContent\\":\\"Build completed successfully\\"}"
            '''
        }

        failure {
            echo 'Pipeline failed - check logs above'

            bat '''
            curl -X POST %API_URL% ^
            -H "Content-Type: application/json" ^
            -d "{\\"pipelineName\\":\\"Jenkins-Pipeline\\",\\"logContent\\":\\"Build failed during pipeline execution\\"}"
            '''
        }

        always {
            echo 'Build process completed'
        }
    }
}
