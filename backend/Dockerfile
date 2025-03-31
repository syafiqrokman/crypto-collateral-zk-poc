# Use OpenJDK 17 base image
FROM eclipse-temurin:17-jdk

# Create a directory for the app
WORKDIR /app

# Copy the built jar (assumes you ran `./gradlew bootJar`)
COPY build/libs/*.jar app.jar

# Expose app port
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
