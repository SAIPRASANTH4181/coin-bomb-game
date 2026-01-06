# Use Maven with Eclipse Temurin JDK 17 for build
FROM maven:3.9-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# Use Eclipse Temurin JRE 17 for runtime
FROM eclipse-temurin:17-jre-alpine

# Copy the built artifact from the previous stage
COPY --from=build /app/target/coin-bomb-backend-0.0.1-SNAPSHOT.jar /app.jar

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the application port
EXPOSE 8080
