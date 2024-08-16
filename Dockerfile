FROM bellsoft/liberica-openjdk-alpine:17.0.8

#Install utilities like curl and jq
RUN apk add curl jq

#Work Directory
WORKDIR /home/mydocker

#Add Files
ADD target/docker-resources     ./
ADD runner.sh                    runner.sh

#EntryPoint
ENTRYPOINT sh runner.sh
