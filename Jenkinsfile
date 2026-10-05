pipeline {

    agent any

    environment {
            JAVA_HOME = 'C:\\Users\\jarevalo\\.jdks\\ms-21.0.12.1'
            PATH = "${JAVA_HOME}\\bin;${env.PATH}"
        }
    parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'edge'],
            description: 'Browser used for automation execution'
        )

        string(
            name: 'TAGS',
            defaultValue: '@regression',
            description: 'Cucumber tag expression'
        )
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run Tests') {
            steps {
                bat """
                    .\\mvnw.cmd clean test ^
                    -Dbrowser=${params.BROWSER} ^
                    -Dcucumber.filter.tags="${params.TAGS}"
                """
            }
        }
    }

    post {

        always {
            archiveArtifacts(
                artifacts: 'target/reports/**/*,target/cucumber-report.html,target/cucumber.json',
                allowEmptyArchive: true
            )
        }
    }
}