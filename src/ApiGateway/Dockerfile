FROM gradle:6.7.1-jdk11 AS dependencies
ENV APP_HOME=/home/gradle/
WORKDIR $APP_HOME

COPY --chown=gradle:gradle build.gradle gradlew gradlew.bat settings.gradle gradle.properties $APP_HOME

COPY --chown=gradle:gradle ./src/ApiGateway/build.gradle $APP_HOME/src/ApiGateway/build.gradle

RUN gradle dependencies --refresh-dependencies --stacktrace --no-daemon

FROM dependencies AS build
ENV APP_HOME=/home/gradle/
WORKDIR $APP_HOME

COPY --chown=gradle:gradle build.gradle gradlew gradlew.bat settings.gradle gradle.properties $APP_HOME

COPY --chown=gradle:gradle ./src/ApiGateway/ $APP_HOME/src/ApiGateway/

RUN gradle build --stacktrace --no-daemon

FROM openjdk:11-jdk-slim AS release
ENV APP_HOME=/usr/gradle/
WORKDIR $APP_HOME
RUN ls -al
COPY --from=build /home/gradle/src/ApiGateway/build/libs/* .

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ApiGateway-0.0.1-SNAPSHOT.jar"]