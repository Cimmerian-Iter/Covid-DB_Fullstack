pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'covid-db-fullstack'
        POSTGRES_CONTAINER = 'postgres-db'
    }

    stages {
        stage('Clone') {
            steps {
                git branch: 'docker', url: 'https://github.com/Cimmerian-Iter/Covid-DB_Fullstack'
            }
        }

        stage('Build') {
            steps {
                script {
                    docker.build(env.DOCKER_IMAGE, "-f Dockerfile .")
                }
            }
        }

        stage('Run') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}
