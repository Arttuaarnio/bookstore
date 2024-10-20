# Use the official Maven image to build the application
FROM maven:3.9.6-openjdk-21-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven project file and download dependencies
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use a slim JDK image to run the application
FROM openjdk:21-jdk-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/bookstore-*.jar bookstore.jar

# Expose the port your application runs on (e.g., 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "bookstore.jar"]
