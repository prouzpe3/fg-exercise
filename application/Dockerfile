FROM maven:3.6.0-jdk-11-slim AS build
COPY src/ src/
COPY pom.xml pom.xml
RUN mvn clean package


FROM adoptopenjdk:11-jre-hotspot
COPY --from=build target/fg-exercise-1.0.0.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]

