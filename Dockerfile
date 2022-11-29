FROM gradle:7.5.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:17.0.1-jdk-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/app/build/libs/app-v1.0.0.jar /app/app.jar
ENTRYPOINT ["java","-cp","/app/app.jar","software_engineering.MainKt"]