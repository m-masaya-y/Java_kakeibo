# -------- Build Stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# 先に pom.xml をコピーして依存関係をキャッシュ
COPY pom.xml .
RUN mvn dependency:go-offline

# プロジェクト一式をコピー
COPY src ./src

# JAR ビルド
RUN mvn clean package -DskipTests


# -------- Run Stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Build で生成された JAR をコピー
COPY --from=build /app/target/*.jar app.jar

# ポート設定（Render / Railway / Heroku 互換）
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
