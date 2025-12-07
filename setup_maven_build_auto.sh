#!/bin/bash
# ======================================================
# setup_maven_build_auto.sh
# macOS 向け Maven 3.9.5 インストール + PATH 設定 + Java_kakeibo ビルド
# プロジェクトディレクトリ自動検出、~/.zshrc 自動作成対応
# ======================================================

set -e

# 1. ホームディレクトリに移動
cd ~

# 2. Maven ダウンロード URL
MAVEN_VERSION=3.9.5
MAVEN_ARCHIVE=apache-maven-$MAVEN_VERSION-bin.tar.gz
MAVEN_URL=https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/$MAVEN_ARCHIVE

# 3. Maven ダウンロード
echo "==> Maven をダウンロード: $MAVEN_URL"
curl -LO $MAVEN_URL

# 4. Maven 解凍
echo "==> Maven を解凍"
tar -xzf $MAVEN_ARCHIVE

# 5. PATH 一時設定（このシェル内のみ）
export PATH="$HOME/apache-maven-$MAVEN_VERSION/bin:$PATH"

# 6. Maven バージョン確認
echo "==> Maven バージョン確認"
mvn -v

# 7. ~/.zshrc がなければ作成して PATH を追記
if [ ! -f "$HOME/.zshrc" ]; then
    echo "==> ~/.zshrc が存在しないので作成"
    touch ~/.zshrc
fi

if ! grep -q "apache-maven-$MAVEN_VERSION/bin" "$HOME/.zshrc"; then
    echo "==> ~/.zshrc に PATH を追加"
    echo 'export PATH="$HOME/apache-maven-'"$MAVEN_VERSION"'/bin:$PATH"' >> ~/.zshrc
    source ~/.zshrc
fi

# 8. プロジェクトディレクトリ自動検出
PROJECT_DIR=$(find ~ -type d -name "Java_kakeibo" 2>/dev/null | head -n 1)

if [ -d "$PROJECT_DIR" ]; then
    echo "==> プロジェクトディレクトリを自動検出: $PROJECT_DIR"
    cd "$PROJECT_DIR"
    echo "==> プロジェクトをビルド: mvn clean package"
    mvn clean package
    echo "==> ビルド完了: target/kakeibo-0.0.1-SNAPSHOT.jar を確認"
    ls -lh target/kakeibo-0.0.1-SNAPSHOT.jar
else
    echo "==> プロジェクトディレクトリ 'Java_kakeibo' が見つかりません"
    echo "手動で確認してください"
fi

echo "==> 完了！"
