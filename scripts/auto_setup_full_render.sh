#!/bin/bash
set -e

# --------------------------------------
# 設定部分
# --------------------------------------
PROJECT_DIR="$HOME/Desktop/Java_kakeibo"   # プロジェクトの場所
RENDER_SERVICE_ID="your-render-service-id" # Render のサービス ID
RENDER_API_KEY="YOUR_RENDER_API_KEY"       # Render API Key
JDK_VERSION="17"
MAVEN_VERSION="3.9.5"

# --------------------------------------
# 0. Homebrew インストール確認 / インストール
# --------------------------------------
if ! command -v brew >/dev/null 2>&1; then
    echo ">>> Homebrew が見つかりません。インストール中..."
    /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
fi

# --------------------------------------
# 1. JDK インストール
# --------------------------------------
if ! /usr/libexec/java_home -v "$JDK_VERSION" >/dev/null 2>&1; then
    echo ">>> OpenJDK $JDK_VERSION をインストール中..."
    brew install openjdk@"$JDK_VERSION"
fi

export JAVA_HOME=$(/usr/libexec/java_home -v "$JDK_VERSION")
export PATH="$JAVA_HOME/bin:$PATH"

echo ">>> Java バージョン確認"
java -version

# --------------------------------------
# 2. Maven インストール
# --------------------------------------
if ! command -v mvn >/dev/null 2>&1; then
    echo ">>> Maven $MAVEN_VERSION をインストール中..."
    curl -L -o /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz
    tar -xzf /tmp/apache-maven-$MAVEN_VERSION-bin.tar.gz -C $HOME
    export PATH="$HOME/apache-maven-$MAVEN_VERSION/bin:$PATH"
fi

echo ">>> Maven バージョン確認"
mvn -v

# --------------------------------------
# 3. プロジェクトディレクトリに移動
# --------------------------------------
if [ ! -d "$PROJECT_DIR" ]; then
    echo "プロジェクトディレクトリが存在しません: $PROJECT_DIR"
    exit 1
fi
cd "$PROJECT_DIR"

# --------------------------------------
# 4. Maven ビルド
# --------------------------------------
echo ">>> Maven ビルド開始"
mvn clean package

# --------------------------------------
# 5. JAR ファイル確認
# --------------------------------------
JAR_FILE=$(ls target/*.jar | grep -v 'original')
if [ ! -f "$JAR_FILE" ]; then
    echo "JAR ファイルが見つかりません: target/*.jar"
    exit 1
fi
echo ">>> JAR ファイル生成済み: $JAR_FILE"

# --------------------------------------
# 6. Render に JAR をアップロード
# --------------------------------------
echo ">>> Render に JAR をアップロード"

UPLOAD_URL=$(curl -s -X POST \
  -H "Authorization: Bearer $RENDER_API_KEY" \
  -H "Accept: application/json" \
  -H "Content-Type: application/json" \
  "https://api.render.com/v1/services/$RENDER_SERVICE_ID/deploys" \
  -d '{"clearCache":true}' | jq -r '.uploadURL')

if [ -z "$UPLOAD_URL" ]; then
    echo "Upload URL の取得に失敗しました"
    exit 1
fi

curl -X PUT --upload-file "$JAR_FILE" "$UPLOAD_URL"

echo ">>> デプロイ開始"
echo "JAR アップロード完了。Render が自動的にデプロイを開始します。"

echo ">>> 完了！"
