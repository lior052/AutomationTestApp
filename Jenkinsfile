pipeline {
    agent any

    environment {
        MAVEN_OPTS = "-Dmaven.repo.local=.m2/repository"
    }

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [[$class: 'SubmoduleOption', recursiveSubmodules: true]],
                    userRemoteConfigs: [[
                        url: 'https://github.com/lior052/AutomationTestApp.git'
                    ]]
                ])
//                 git branch: 'main', url: 'https://github.com/lior052/AutomationTestApp.git'
            }
        }

        stage('Install SDK') {
            steps {
                dir('AppiumSDK') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -DsuiteXmlFile=src/test/testng/testng.xml'
            }
        }

        stage('Archive Results') {
            steps {
                // Optional: Only include if you actually have XML results in surefire-reports
                junit '**/target/surefire-reports/*.xml'

                // Archive HTML and JSON report files
                archiveArtifacts artifacts: 'reports/**/*.html, reports/**/*.json', allowEmptyArchive: true
            }
        }

        stage('Publish HTML Report') {
            steps {
                // Publish the main dashboard report
                publishHTML([
                    reportDir: 'reports',
                    reportFiles: 'dashboard.html',
                    reportName: 'Automation Test Dashboard',
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    allowMissing: false
                ])
            }
        }


    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
