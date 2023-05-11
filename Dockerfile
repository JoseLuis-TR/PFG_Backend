FROM openjdk:11-jdk-slim
COPY . /app
WORKDIR /app
RUN chmod +x mvnw && ./mvnw clean install
EXPOSE 8080
CMD ["java", "-jar", "target/PFG_haven-0.0.1-SNAPSHOT.jar"]
