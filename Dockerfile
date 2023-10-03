# Build stage
#
FROM openjdk:20
ADD ./api-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]