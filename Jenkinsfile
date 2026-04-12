pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'Java-17'
    }

    stages {

        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/Harshitha2105/ci-cd-failure-analyzer.git'
            }
        }

        stage('Debug Workspace') {
            steps {
                bat 'dir'
                bat 'dir backend'
                bat 'dir frontend'
            }
        }

        stage('Build Backend') {
            steps {
                script {
                    if (fileExists('backend/pom.xml')) {
                        bat 'cd backend && mvn clean package -DskipTests'
                    } else {
                        error "backend/pom.xml NOT FOUND - checkout issue"
                    }
                }
            }
        }

        stage('Verify Backend Jar') {
            steps {
                bat 'dir backend\\target'
            }
        }

        stage('Frontend Check') {
            steps {
                bat 'dir frontend'
            }
        }

        stage('Docker Compose Down') {
            steps {
                bat 'docker-compose down || exit 0'
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
            echo 'Pipeline failed - check logs above'
        }
        always {
            echo 'Build process completed'
        }
    }
}
