FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/discovery-server-0.0.1-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/lib/
ARG DISCOVERY_PORT=8761

WORKDIR /home/sbelykova/work/code/miscroservices/docker

COPY ${JAR_FILE} discovery.jar

ADD ${JAR_LIB_FILE} /lib

ENTRYPOINT ["java", "-jar", "discovery.jar"]

EXPOSE ${DISCOVERY_PORT}