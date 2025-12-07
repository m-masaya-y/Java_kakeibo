# 1. Eclipse Temurin JDK 17 を使用
FROM eclipse-temurin:17-jdk

# 2. 作業ディレクトリを作成
WORKDIR /app

# 3. ビルド済みの JAR をコンテナ内にコピー
COPY target/kakeibo-0.0.1-SNAPSHOT.jar ./app.jar

# 4. 環境変数（ポート番号）
ENV PORT=8080

# 5. アプリ起動コマンド
CMD ["java", "-jar", "app.jar"]
