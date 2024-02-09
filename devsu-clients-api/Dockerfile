FROM openjdk:17-jdk-slim
COPY devsu-clients-api/target/clients-api-1.0.0.jar clients-api-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/clients-api-1.0.0.jar"]