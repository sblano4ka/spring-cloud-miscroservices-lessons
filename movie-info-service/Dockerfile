FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/movie-info-service-0.0.1-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/lib/
ARG MOVIE_INFO_PORT=8083

WORKDIR /home/sbelykova/work/code/miscroservices/docker

COPY ${JAR_FILE} movie-info.jar

ADD ${JAR_LIB_FILE} /lib

ENTRYPOINT ["java", "-jar", "movie-info.jar"]

EXPOSE ${MOVIE_INFO_PORT}