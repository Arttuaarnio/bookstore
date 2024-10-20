#
# Mvn Build
#
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /home/app

# Copy the Maven project file and source code
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package

#
# Jar Package
#
FROM eclipse-temurin:21-jre

# Copy the jar file from the build stage
# bookstore-<version>.jar = bookstore-<version>.jar (pom.xml)
COPY --from=build /home/app/target/bookstore-*.jar /usr/local/lib/bookstore.jar

# Expose the port your application runs on (e.g., 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/usr/local/lib/bookstore.jar"]
