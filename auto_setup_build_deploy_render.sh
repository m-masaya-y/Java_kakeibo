#!/bin/bash

set -e

# ===== 設定 =====
PROJECT_DIR=~/Desktop/Java_kakeibo
MAVEN_VERSION=3.9.5
DOCKER_IMAGE_NAME=java_kakeibo
RENDER_SERVICE_NAME=Java_kakeibo-1
RENDER_REGION="Virginia"

# ===== 1️⃣ JDK 17 確認 =====
echo "🟢 1️⃣ JDK 17 確認"
if ! java -version 2>&1 | grep '17' >/dev/null; then
    echo "JDK 17 が見つかりません。Homebrew でインストールします..."
    /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
    brew install --cask temurin17
fi
java -version

# ===== 2️⃣ Maven 確認 =====
echo "🟢 2️⃣ Maven 確認"
if ! mvn -v >/dev/null 2>&1; then
    echo "Maven が見つかりません。ダウンロードしてセットアップします..."
    mkdir -p ~/apache-maven-temp
    curl -L https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz -o ~/apache-maven-temp/maven.tar.gz
    tar -xzf ~/apache-maven-temp/maven.tar.gz -C ~/
    echo 'export PATH=$HOME/apache-maven-'$MAVEN_VERSION'/bin:$PATH' >> ~/.zshrc
    export PATH=$HOME/apache-maven-$MAVEN_VERSION/bin:$PATH
fi
mvn -v

# ===== 3️⃣ Docker 確認 =====
echo "🟢 3️⃣ Docker 確認"
if ! docker -v >/dev/null 2>&1; then
    echo "Docker が見つかりません。公式サイトからインストールしてください: https://www.docker.com/products/docker-desktop"
    exit 1
fi
docker -v

# ===== 4️⃣ プロジェクトディレクトリ確認 =====
echo "🟢 4️⃣ プロジェクトディレクトリ確認"
if [ ! -d "$PROJECT_DIR" ]; then
    echo "プロジェクトディレクトリ $PROJECT_DIR が存在しません。作成してから再実行してください。"
    exit 1
fi
cd "$PROJECT_DIR"

# ===== 5️⃣ Maven ビルド =====
echo "🟢 5️⃣ Maven ビルド"
mvn clean package

# ===== 6️⃣ Docker イメージ作成 =====
echo "🟢 6️⃣ Docker イメージ作成"
docker build -t $DOCKER_IMAGE_NAME .

# ===== 7️⃣ Docker コンテナ確認 =====
echo "🟢 7️⃣ Docker コンテナ起動確認 (ポート 8080)"
docker run --rm -p 8080:8080 $DOCKER_IMAGE_NAME & sleep 5
echo "http://localhost:8080 で動作確認できます。コンテナは Ctrl+C で停止"

# ===== 8️⃣ Render CLI インストール確認 =====
echo "🟢 8️⃣ Render CLI 確認"
if ! render --version >/dev/null 2>&1; then
    echo "Render CLI が見つかりません。インストールします..."
    curl -L https://render.com/cli/install.sh | bash
fi
render --version

# ===== 9️⃣ Render デプロイ =====
echo "🟢 9️⃣ Render デプロイ準備"
echo "Render CLI にログインしてください:"
echo "render login"
echo "その後、以下のコマンドで新規サービスを作成できます:"
echo "render services create web --name $RENDER_SERVICE_NAME --repo https://github.com/YOUR_USERNAME/Java_kakeibo --branch main --region $RENDER_REGION --build-command 'mvn clean package' --start-command 'java -jar target/kakeibo-0.0.1-SNAPSHOT.jar'"

echo "✅ 自動セットアップ完了！"
