#!/bin/bash

# ========================================
# auto_setup_build_deploy_render.sh
# Java_kakeibo 用完全自動セットアップ・ビルド・Docker作成・Renderデプロイ
# ========================================

PROJECT_DIR=~/Desktop/Java_kakeibo
DOCKER_IMAGE_NAME=kakeibo-app
JAR_NAME=target/kakeibo-0.0.1-SNAPSHOT.jar
RENDER_SERVICE_NAME=Java_kakeibo-1   # Render側で作成済みのサービス名

# 1️⃣ JDK確認
echo "☑ JDK確認..."
if ! command -v java >/dev/null 2>&1; then
    echo "❌ Javaが見つかりません。setup_jdk17.sh を実行してください。"
    exit 1
fi
java -version

# 2️⃣ Maven確認
echo "☑ Maven確認..."
if ! command -v mvn >/dev/null 2>&1; then
    echo "❌ Mavenが見つかりません。setup_maven.sh を実行してください。"
    exit 1
fi
mvn -v

# 3️⃣ プロジェクトディレクトリへ移動
cd "$PROJECT_DIR" || { echo "❌ プロジェクトディレクトリが見つかりません"; exit 1; }

# 4️⃣ Mavenでビルド
echo "🛠 Mavenビルド開始..."
mvn clean package -DargLine="--enable-native-access=ALL-UNNAMED"
if [ $? -ne 0 ]; then
    echo "❌ Mavenビルド失敗"
    exit 1
fi

# 5️⃣ JAR確認
if [ ! -f "$JAR_NAME" ]; then
    echo "❌ JARファイルが生成されませんでした: $JAR_NAME"
    exit 1
fi
echo "✅ Mavenビルド成功: $JAR_NAME"

# 6️⃣ Dockerfile確認
if [ ! -f "Dockerfile" ]; then
    echo "❌ Dockerfileがありません"
    exit 1
fi

# 7️⃣ Dockerイメージ作成
echo "🐳 Dockerイメージ作成: $DOCKER_IMAGE_NAME"
docker build -t "$DOCKER_IMAGE_NAME" .
if [ $? -ne 0 ]; then
    echo "❌ Dockerビルド失敗"
    exit 1
fi
echo "✅ Dockerイメージ作成成功: $DOCKER_IMAGE_NAME"

# 8️⃣ Render CLI確認
if ! command -v render >/dev/null 2>&1; then
    echo "❌ Render CLI が見つかりません。 https://render.com/docs/cli を参照してインストールしてください"
    exit 1
fi

# 9️⃣ Render にログインしているか確認
render whoami >/dev/null 2>&1
if [ $? -ne 0 ]; then
    echo "❌ Render CLI にログインしていません"
    echo "ログインしてください: render login"
    exit 1
fi

# 🔟 Render に Docker イメージをデプロイ
echo "🚀 Render へデプロイ..."
render services update "$RENDER_SERVICE_NAME" --image "$DOCKER_IMAGE_NAME"
if [ $? -eq 0 ]; then
    echo "🎉 Render デプロイ完了！"
else
    echo "❌ Render デプロイ失敗"
    exit 1
fi

echo "✅ 全工程完了！"
