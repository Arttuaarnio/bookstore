FROM maven:4.0.0-openjdk-21-slim AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/bookstore-*.jar bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "bookstore.jar"]
