pipeline {
    agent any

    environment {
        MAVEN_HOME = tool 'maven3'  // Ensure Maven is installed in Jenkins
        SCANNER_HOME = tool 'sonar-scanner' // Replace with the name of your SonarQube server in Jenkins
    }

    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/adesh202/employee-management.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    sh 'mvn test'
                }
                // Publish test results
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Code Coverage') {
            steps {
                script {
                    sh 'mvn jacoco:prepare-agent test jacoco:report'
                }
                // Optionally, archive reports
                archiveArtifacts artifacts: '**/target/site/jacoco/*', allowEmptyArchive: true
            }
        }
        
        stage('OWASP Dependency-Check') {
            steps {
                dependencyCheck additionalArguments: '', odcInstallation: 'DC'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    sh 'mvn package -DskipTests'
                }
                archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Add deployment steps
            }
        }
    }

    post {
    always {
        emailext(
            subject: "Pipeline Status: ${BUILD_NUMBER}",
            body: '''
                <html>
                    <body>
                        <p>Build Status: ${BUILD_STATUS}</p>
                        <p>Build Number: ${BUILD_NUMBER}</p>
                        <p>Check the <a href="${BUILD_URL}">console output</a></p>
                    </body>
                </html>
            ''',
            to: 'adesh.metagates@gmail.com',
            from: 'jenkins@example.com',
            replyTo: 'jenkins@example.com',
            mimeType: 'text/html'
        )
    }
}

}
