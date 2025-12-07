# -------- Build Stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# プロジェクトをコピー
COPY pom.xml .
COPY src ./src

# JAR ビルド
RUN mvn clean package -DskipTests


# -------- Run Stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

# build で作られた JAR をコピー
COPY --from=build /app/target/kakeibo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
