# -------- Build stage --------
FROM eclipse-temurin:17-jdk AS builder

# 作業ディレクトリ
WORKDIR /app

# プロジェクト一式コピー
COPY . .

# Maven wrapper 実行権限
RUN chmod +x mvnw

# jar をビルド
RUN ./mvnw clean package -DskipTests

# -------- Run stage --------
FROM eclipse-temurin:17-jre

WORKDIR /app

# builderでできた jar をコピー
COPY --from=builder /app/target/kakeibo-0.0.1-SNAPSHOT.jar app.jar

# ポート
EXPOSE 8080

# 起動コマンド
ENTRYPOINT ["java", "-jar", "app.jar"]
