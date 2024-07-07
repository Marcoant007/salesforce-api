FROM maven:3.8.4-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar /app/application.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "/app/application.jar"]
