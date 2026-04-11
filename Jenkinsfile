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

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t cicd-backend .'
            }
        }

        stage('Run Container') {
            steps {
                bat 'docker run -d -p 8080:8080 cicd-backend'
            }
        }
    }

    post {
        failure {
            echo "❌ Pipeline failed. Sending logs to Failure Analyzer..."

            bat '''
            curl -X POST http://localhost:8080/api/logs/analyze \
            -H "Content-Type: application/json" \
            -d '{
                "pipelineName": "Jenkins-Build",
                "logContent": "Jenkins pipeline failed during build or test stage"
            }'
            '''
        }

        success {
            echo "✅ Pipeline executed successfully"
        }
    }
}
