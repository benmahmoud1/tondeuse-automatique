pipeline {
    agent any

    tools {
        maven "Maven 3.9.7"
        jdk "jdk-1.17"
    }

    stages {
        stage("clone code") {
            steps {
                script {
                    
                    git 'https://github.com/benmahmoud1/tondeuse-automatique.git'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') { 
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy application') { 
            steps {
                sh 'java -cp target/tondeuse-app.jar com.tendeuse_automatique.Main'
            }
        }
    }
}