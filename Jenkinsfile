pipeline {

    parameters {
        string(name: 'SELENOID_URL', defaultValue: 'docker.lan', description: 'URL for selenoid instance')
    }

    agent any

    triggers {
        pollSCM('* * * * *') //polling for changes, here once a minute
    }

    stages {
        stage('Checkout') {
            steps { //Checking out the repo
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: 'https://github.com/Thascalos/autotests_example.git']]])             }
        }
        stage('Unit & Integration Tests') {
            steps {
                script {
                    try {
                        sh 'chmod +x gradlew'
                        sh './gradlew clean test --no-daemon' //run a gradle task
                    } finally {
                        junit allowEmptyResults: true, testResults: '**/build/test-results/*.xml'
                    }
                }
            }
        }
        stage('End 2 End Tests') {
            stages {
                stage('Checking non-auth tests') {
                    steps {
                        script {
                            sh '''./gradlew RunAllWithoutAuth -DisHeadless=false -Dselenoid_url="''' + params.SELENOID_URL + '''" --no-daemon'''
                        }
                    }
                }
                stage('Checking tests with auth') {
                    steps {
                        script {
                            sh '''./gradlew RunAllWithAuth -DisHeadless=false -Dselenoid_url="''' + params.SELENOID_URL + '''" --no-daemon'''
                        }
                    }
                }
            }
        }
    }
    post {
        always {
                 allure([
                     includeProperties: false,
                     jdk: '',
                     properties: [],
                     reportBuildPolicy: 'ALWAYS',
                     results: [[path: 'build/allure-results']]
                 ])
        }
    }
}