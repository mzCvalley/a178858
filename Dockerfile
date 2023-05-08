FROM maven:3.8.4-openjdk-17 AS build

# Specify the working directory
WORKDIR /app

# Copy the pom.xml file into the working directory
COPY pom.xml .

# Download the project's dependencies while leveraging Docker cache
RUN mvn -B dependency:go-offline

# Copy the rest of the project files
COPY src /app/src

# Build the project
RUN mvn -B clean package

# Use the OpenJDK 17 base image for the final image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built jar file from the build stage into the working directory
COPY --from=build /app/target/*.jar app.jar

# Expose the port which the application will run on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]