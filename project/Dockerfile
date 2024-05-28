# Use a imagem oficial do Maven 3.6.3 com JDK 11 como base
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]