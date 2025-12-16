# Stage 1: Build with Gradle 9.2.1 and JDK 21
FROM gradle:9.2.1-jdk21 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app
RUN gradle bootJar --no-daemon

# Stage 2: Runtime with JDK 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar demo-filestore-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "demo-filestore-service.jar"]
