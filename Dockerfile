# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim
ENV SPRING_PROFILES_ACTIVE=container
ENV MONOGDB_URI mongodb://localhost:27017/kratos
ENV MONGODB_USERNAME MONGO
ENV MONGODB_PASSWORD MONGO
# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files
COPY build.gradle settings.gradle /app/

# Copy the gradle wrapper
COPY gradlew /app/
COPY gradle /app/gradle

# Download the dependencies
RUN ./gradlew dependencies

# Copy the rest of the application code
COPY . /app

# Build the application
RUN ./gradlew build -x test

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "build/libs/kratos-backend-0.0.1-SNAPSHOT.jar"]