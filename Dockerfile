# Use an official Java runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/vulnapp-0.0.1-SNAPSHOT.jar /app/vulnapp.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/vulnapp.jar"]