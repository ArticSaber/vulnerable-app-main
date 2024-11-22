pipeline {
    agent any
    tools { 
        maven 'maven'  // Ensure 'maven' is the name of your Maven tool in Jenkins
    }
    
    environment {
        SNYK_SEVERITY_THRESHOLD = 'high' // Threshold for Snyk failures
    }

    stages {
        stage('Compile and Run Sonar Analysis') {
            steps {	
                sh 'mvn clean verify sonar:sonar ' +
                   '-Dsonar.projectKey=sampleapp-devsecops_sampleproject-devsecops ' +
                   '-Dsonar.organization=sampleapp-devsecops ' +
                   '-Dsonar.host.url=https://sonarcloud.io ' +
                   '-Dsonar.token=c1384e5de845c5471b5b72ce18a40df7eaee680c'
            }
        } 

        stage('Run SCA Analysis Using Snyk') {
            steps {		
                script {
                    // Ensure mvnw has execute permissions
                    sh 'chmod +x mvnw'
                }
                withCredentials([string(credentialsId: 'SNYK_TOKEN', variable: 'SNYK_TOKEN')]) {
                    // Run Snyk test and fail the build for high-severity issues
                    sh 'mvn snyk:test --severity-threshold=${SNYK_SEVERITY_THRESHOLD}'
                }
            }
        }		
    }

    post {
        always {
            // Archive Snyk report for review
            archiveArtifacts artifacts: 'snyk-report.json', fingerprint: true
        }
        failure {
            // Notify about build failure
            echo 'Build failed due to security vulnerabilities or other issues!'
        }
        success {
            echo 'Build and analysis completed successfully!'
        }
    }
}
