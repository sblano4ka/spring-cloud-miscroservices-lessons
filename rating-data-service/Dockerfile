FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/rating-data-service-0.0.1-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/lib/
ARG RATING_SERVICE_PORT=8082

WORKDIR /home/sbelykova/work/code/miscroservices/docker

COPY ${JAR_FILE} rating.jar
ADD ${JAR_LIB_FILE} /lib

ENTRYPOINT ["java", "-jar", "rating.jar"]

EXPOSE ${RATING_SERVICE_PORT}