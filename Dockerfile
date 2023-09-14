FROM openjdk:20-jdk-buster
ARG JAR-FILE=target/*.jar

COPY ./target/treshermanitos-0.0.1.jar /app/

WORKDIR /app


CMD ["java", "-jar", "treshermanitos-0.0.1.jar"]
