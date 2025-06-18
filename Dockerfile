# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the fat JAR file into the container at /app
# Make sure your build process (e.g., mvn package or ./gradlew bootJar)
# produces a JAR file in the 'target' directory.
# Adjust the JAR_FILE path if your JAR is named differently or located elsewhere.
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "application.jar"]