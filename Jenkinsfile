pipeline {

    agent any

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