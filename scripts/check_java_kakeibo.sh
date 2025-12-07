#!/bin/bash

echo "===== 1. Java バージョン確認 ====="
if command -v java &> /dev/null; then
java -version
else
echo "Java が見つかりません。インストールしてください。"
fi

echo
echo "===== 2. Maven バージョン確認 ====="
if command -v mvn &> /dev/null; then
mvn -v
else
echo "Maven が見つかりません。インストールしてください。"
fi

echo
echo "===== 3. プロジェクトビルド確認 ====="
PROJECT_DIR=~/Desktop/Java_kakeibo
if [ -d "$PROJECT_DIR" ]; then
cd "$PROJECT_DIR" || exit
if [ -f target/kakeibo-0.0.1-SNAPSHOT.jar ]; then
echo "JAR ファイルが存在します: target/kakeibo-0.0.1-SNAPSHOT.jar"
else
echo "JAR ファイルがまだ生成されていません。Maven ビルドを確認してください。"
fi
else
echo "プロジェクトディレクトリ $PROJECT_DIR が見つかりません。"
fi

echo
echo "===== 4. Render デプロイ準備確認 ====="

# Render デプロイ用の JAR があればチェック

if [ -f "$PROJECT_DIR/target/kakeibo-0.0.1-SNAPSHOT.jar" ]; then
echo "Render にアップロードする準備は OK"
echo "Render ダッシュボードでサービスステータスを確認してください"
else
echo "Render 用 JAR がありません。ビルドを確認してください"
fi

echo
echo "===== チェック完了 ====="
