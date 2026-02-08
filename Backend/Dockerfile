# 1️⃣ Build stage
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy only required files
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw

# Download dependencies first (faster builds)
RUN ./mvnw dependency:go-offline

# Copy source code
COPY src src

# Build jar
RUN ./mvnw clean package -DskipTests

# 2️⃣ Runtime stage (smaller image)
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
