#
# Mvn Build
#
FROM maven:4.0.0-openjdk-21 AS build 
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Jar Package
#
FROM openjdk-21
COPY --from=build /home/app/target/bookstore.jar /usr/local/lib/bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/bookstore.jar"]