#
# Build stage
#
FROM maven:3.8.4-openjdk-17 AS build
# Copy the files into the working directory and build package
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
# Use the OpenJDK 17 base image for the final image
FROM openjdk:17-jdk-alpine
# Copy the built jar file from the build stage into the working directory
COPY --from=build /target/a178858-1.0.0.jar app.jar
# Expose the port which the application will run on
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
