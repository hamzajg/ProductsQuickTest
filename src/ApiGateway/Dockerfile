FROM openjdk:11-jdk-slim
ADD src/ApiGateway/build/libs/ApiGateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENTRYPOINT ["java", "-jar", "/apigateway.jar"]
EXPOSE 8080