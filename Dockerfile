# Use JDK 21 as the base image
FROM eclipse-temurin:21-jdk-jammy

# Set volume for temporary files
VOLUME /tmp

# Build the application
# We use the JAR file generated from mvn clean package
COPY target/Movie_TicketBooking_Final-0.0.1-SNAPSHOT.jar app.jar

# Run the jar, listening on the PORT provided by the environment
ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "/app.jar"]
