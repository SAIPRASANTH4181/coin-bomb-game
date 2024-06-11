# Use the official Maven image to create a build artifact
FROM maven:3.8.1-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Copy the built artifact from the previous stage
COPY --from=build /app/target/coin-bomb-backend-0.0.1-SNAPSHOT.jar /app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the application port
EXPOSE 8080
