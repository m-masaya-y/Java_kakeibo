#!/bin/bash
# setup_maven.sh
# Java/Maven プロジェクトをビルドするスクリプト
# pom.xml の確認、自動ディレクトリ移動、Maven のダウンロード・セットアップを行う

# -------------------------------
# 1. プロジェクトディレクトリ設定
# -------------------------------
PROJECT_DIR="$HOME/Desktop/Java_kakeibo"

if [ ! -d "$PROJECT_DIR" ]; then
  echo "プロジェクトディレクトリが存在しません: $PROJECT_DIR"
  echo "手動で作成して pom.xml を置いてください"
  exit 1
fi

cd "$PROJECT_DIR" || exit 1

# -------------------------------
# 2. pom.xml の確認
# -------------------------------
if [ ! -f "pom.xml" ]; then
  echo "pom.xml が見つかりません。プロジェクトルートに置いてください"
  exit 1
fi

# -------------------------------
# 3. Maven が存在するか確認
# -------------------------------
if ! command -v mvn &> /dev/null
then
    echo "Maven が見つかりません。ダウンロードします..."
    MAVEN_VER=3.9.5
    MAVEN_DIR="$HOME/apache-maven-$MAVEN_VER"
    MAVEN_TAR="$HOME/apache-maven-$MAVEN_VER-bin.tar.gz"
    
    # ダウンロード
    curl -L "https://archive.apache.org/dist/maven/maven-3/$MAVEN_VER/binaries/apache-maven-$MAVEN_VER-bin.tar.gz" -o "$MAVEN_TAR"
    
    # 解凍
    tar -xzf "$MAVEN_TAR" -C "$HOME"
    
    # PATH に追加
    export PATH="$MAVEN_DIR/bin:$PATH"
    echo "export PATH=\"$MAVEN_DIR/bin:\$PATH\"" >> "$HOME/.zshrc"
    echo "Maven をセットアップしました: $MAVEN_DIR"
fi

# -------------------------------
# 4. Maven ビルド
# -------------------------------
echo "プロジェクトをビルド中..."
mvn clean package

if [ $? -eq 0 ]; then
    echo "ビルド成功！ target ディレクトリに jar ファイルが生成されました"
else
    echo "ビルド失敗。ログを確認してください"
fi
