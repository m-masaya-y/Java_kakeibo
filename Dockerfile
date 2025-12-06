FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/kakeibo-0.0.1-SNAPSHOT.jar ./app.jar
ENV PORT=8080
CMD ["java", "-jar", "app.jar"]
