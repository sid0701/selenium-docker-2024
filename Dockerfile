FROM bellsoft/liberica-openjdk-alpine:21

#Install utilities like curl and jq
RUN apk add curl jq

#Work Directory
WORKDIR /home/mydocker

#Add Files
ADD target/docker-resources     ./
ADD run.sh                    run.sh

#EntryPoint
ENTRYPOINT sh run.sh
