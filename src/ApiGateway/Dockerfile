FROM gradle:7.6.4-jdk17 AS dependencies
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

RUN gradle build --stacktrace --no-daemon -x test

FROM eclipse-temurin:17-jre AS release
ENV APP_HOME=/app/
WORKDIR $APP_HOME
# Gradle 7 also writes a *-plain.jar (no dependencies); keep only the
# runnable one so the jar name does not have to be tracked here.
COPY --from=build /home/gradle/src/ApiGateway/build/libs/ ./libs/
RUN cp "$(ls ./libs/*.jar | grep -v -- '-plain' | head -1)" ./app.jar && rm -rf ./libs

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
