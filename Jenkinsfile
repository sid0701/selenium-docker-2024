pipeline{
    agent any

    stages{
        stage("build jars"){
            steps{
                sh "mvn clean package -DskipTests"
            }
        }
        stage("build image"){
            steps{
                sh "docker build -t=sid0701/docker2024:latest ."
            }
        }
        stage("push image"){
            environment{
                DOCKER_HUB = credentials('mydocker-credentials')
            }
            steps{
                	sh 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
                	sh"docker push sid0701/docker2024:latest"
                    sh "docker tag sid0701/docker2024:latest sid0701/docker2024:${env.BUILD_NUMBER}"
                    sh "docker push sid0701/docker2024:${env.BUILD_NUMBER}"

            }
        }
        }

    post{
        always{
            sh "docker logout"
        }
    }
}