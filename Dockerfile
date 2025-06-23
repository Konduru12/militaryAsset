# Use official JDK image
FROM eclipse-temurin:21-jdk

# Set the JAR file path
ARG JAR_FILE=target/militaryAsset-0.0.1-SNAPSHOT.jar

# Copy the JAR to the container
COPY ${JAR_FILE} app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app.jar"]
