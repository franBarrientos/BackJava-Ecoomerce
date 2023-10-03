# Build stage
#
FROM openjdk:20
ADD ./api-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]