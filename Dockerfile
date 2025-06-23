# Use official JDK image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the wrapper and set execute permission
COPY .mvn/ .mvn
COPY mvnw .
RUN chmod +x mvnw

# Copy the rest of the project
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Run the jar
CMD ["java", "-jar", "target/militaryAsset-0.0.1-SNAPSHOT.jar"]
