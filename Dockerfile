FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# プロジェクトファイルをコピー
COPY . .

# Maven をインストール
RUN apt-get update && apt-get install -y maven

# JAR をビルド
RUN mvn clean package -DskipTests

# 実行用ステージ
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/kakeibo-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
