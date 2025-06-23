# Use official JDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Give execution permission to mvnw
RUN chmod +x mvnw

# Build the project (skip tests)
RUN ./mvnw clean package -DskipTests

# Run the jar
ENTRYPOINT ["java", "-jar", "target/militaryAsset-0.0.1-SNAPSHOT.jar"]
