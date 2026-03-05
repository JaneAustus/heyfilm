# Use JDK 21 as the base image
FROM eclipse-temurin:21-jdk-jammy

# Copy the JAR file built from Maven
COPY target/Movie_TicketBooking_Final-0.0.1-SNAPSHOT.jar app.jar

# Run the jar using Railway PORT
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-8080} -jar /app.jar"]
