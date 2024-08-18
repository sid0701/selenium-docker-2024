pipeline{
    agent any

    stages{
        stage("build jars"){
            steps{
                bat "mvn clean package -DskipTests"
            }
        }
        stage("build image"){
            steps{
                bat "docker build -t=sid0701/docker2024 ."
            }
        }
        stage("push image"){
            environment{
                DOCKER_HUB = credentials('mydocker-credentials')
            }
            steps{
                	bat ‘docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%’
                	bat “docker push sid0701/docker2024”

            }
        }
        }
}
