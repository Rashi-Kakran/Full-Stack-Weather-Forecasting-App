# Use Java 17 JDK
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy all files to container
COPY . .

# Make Maven wrapper executable (important for Linux builds)
RUN chmod +x mvnw

# Build project using Maven wrapper, skip tests
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the generated jar (use exact name of your jar)
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]