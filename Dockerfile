# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:17-jre-focal
ENV SPRING_PROFILES_ACTIVE=container
ENV MONOGDB_URI mongodb://localhost:27017/kratos
ENV MONGODB_USERNAME MONGO
ENV MONGODB_PASSWORD MONGO

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the GitHub Action artifact
COPY kratos-backend.jar /app/kratos-backend.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/kratos-backend.jar"]