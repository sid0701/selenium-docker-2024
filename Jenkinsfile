pipeline{
    agent any

    stages{
        stage("build jars"){
            agent{
                docker {
                    image 'maven:3.9.8-eclipse-temurin-17-focal'
                    args '-u root -v /tmp/m2:/root/.m2'
                }
            }
            steps{
                sh "mvn clean package -DskipTests"
            }
        }
        stage("build image"){
            steps{
                script {
                    app = docker.build('sid0701/docker2024')
                }
            }
        }
        stage("push image"){
            steps{
                script {
                docker.withRegistry('','mydocker-credentials') {
                    app.push("latest")
                }
            }
            }
        }
        }
}