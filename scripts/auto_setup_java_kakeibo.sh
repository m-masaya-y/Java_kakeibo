#!/bin/bash
set -e

# -----------------------------------
# 1. 設定
# -----------------------------------
JDK_VERSION=17
MAVEN_VERSION=3.9.5
PROJECT_DIR="$HOME/Projects/Java_kakeibo"
JDK_INSTALL_DIR="$HOME/jdk-$JDK_VERSION"
MAVEN_INSTALL_DIR="$HOME/apache-maven-$MAVEN_VERSION"

# -----------------------------------
# 2. JDK インストール
# -----------------------------------
echo "==> JDK $JDK_VERSION をダウンロードして展開"
JDK_TEMP="$HOME/.jdk-temp"
mkdir -p $JDK_TEMP
cd $JDK_TEMP

# Intel / Apple Silicon 共通の Temurin JDK 17 URL
JDK_URL="https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.8%2B7/OpenJDK17U-jdk_x64_mac_hotspot_17.0.8_7.tar.gz"

curl -L -o jdk.tar.gz $JDK_URL
mkdir -p $JDK_INSTALL_DIR
tar -xzf jdk.tar.gz -C $JDK_INSTALL_DIR --strip-components=1

# -----------------------------------
# 3. Maven インストール
# -----------------------------------
echo "==> Maven $MAVEN_VERSION をダウンロードして展開"
MAVEN_TEMP="$HOME/.maven-temp"
mkdir -p $MAVEN_TEMP
cd $MAVEN_TEMP

MAVEN_URL="https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz"
curl -L -o apache-maven.tar.gz $MAVEN_URL
mkdir -p $MAVEN_INSTALL_DIR
tar -xzf apache-maven.tar.gz -C $MAVEN_INSTALL_DIR --strip-components=1

# -----------------------------------
# 4. 環境変数設定
# -----------------------------------
export JAVA_HOME=$JDK_INSTALL_DIR
export PATH=$JAVA_HOME/bin:$MAVEN_INSTALL_DIR/bin:$PATH

echo "==> JAVA_HOME: $JAVA_HOME"
echo "==> PATH に Maven を追加: $MAVEN_INSTALL_DIR/bin"

# -----------------------------------
# 5. pom.xml の java.version を 17 に更新
# -----------------------------------
if [ -f "$PROJECT_DIR/pom.xml" ]; then
    echo "==> pom.xml の java.version を 17 に更新"
    sed -i.bak -E "s|<java.version>.*</java.version>|<java.version>17</java.version>|" "$PROJECT_DIR/pom.xml"
else
    echo "pom.xml が見つかりません: $PROJECT_DIR"
    exit 1
fi

# -----------------------------------
# 6. プロジェクトビルド
# -----------------------------------
cd $PROJECT_DIR
echo "==> Maven ビルド開始: mvn clean package"
mvn clean package

echo "==> ビルド完了: target/*.jar を確認してください"
