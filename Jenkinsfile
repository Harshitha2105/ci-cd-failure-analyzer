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
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t cicd-backend .'
            }
        }

        stage('Run Container') {
            steps {
                sh 'docker run -d -p 8080:8080 cicd-backend'
            }
        }
    }

    post {
        failure {
            echo "❌ Pipeline failed. Sending logs to Failure Analyzer..."

            sh '''
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
