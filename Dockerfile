# Use the Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Use the OpenJDK image to run the application
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/festejandoando-0.0.1-SNAPSHOT.jar festejandoando.jar

# Set the PORT environment variable (adjust it as needed)
ENV PORT=8080

# Expose the port specified by Railway
EXPOSE $PORT

# Configure the Java application to listen on host 0.0.0.0 and the PORT environment variable
CMD ["java", "-jar", "festejandoando.jar", "--host", "0.0.0.0", "--port", "$PORT"]
