FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/movie-catalog-service-0.0.1-SNAPSHOT.war
ARG JAR_LIB_FILE=target/lib/
ARG CATALOG_PORT=8081

WORKDIR /home/sbelykova/work/code/miscroservices/docker

COPY ${JAR_FILE} catalog.jar

ADD ${JAR_LIB_FILE} /lib

ENTRYPOINT ["java", "-jar", "catalog.jar"]

EXPOSE ${CATALOG_PORT}