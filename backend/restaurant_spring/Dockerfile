FROM ubuntu:latest
FROM openjdk:17
ADD target/springboot-mysql-docker.jar springboot-mysql-docker.jar


ENTRYPOINT ["java", "-jar","/springboot-mysql-docker.jar"]
LABEL authors="HUNGDO"