# Use official JDK image
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy everything from your project into the container
COPY . .

# Build the JAR using Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Run the built JAR file
ENTRYPOINT ["java", "-jar", "target/militaryAsset-0.0.1-SNAPSHOT.jar"]
