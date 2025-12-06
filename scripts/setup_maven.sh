#!/bin/bash

# Homebrew のパス自動判定
if [ -d "/usr/local/Homebrew" ]; then
  BREW_PATH="/usr/local/Homebrew"
elif [ -d "/opt/homebrew" ]; then
  BREW_PATH="/opt/homebrew"
else
  echo "Homebrew が見つかりません。先にインストールしてください。"
  exit 1
fi
echo "Homebrew パス: $BREW_PATH"

# 所有権を自分に変更
sudo chown -R $(whoami) $BREW_PATH
sudo chmod -R u+w $BREW_PATH

# Git safe.directory 設定
git config --global --add safe.directory $BREW_PATH/Library/Taps/homebrew/homebrew-core

# shallow clone 解消
git -C $BREW_PATH/Library/Taps/homebrew/homebrew-core fetch --unshallow

# Homebrew 更新
brew update

# Maven インストール
brew install maven

# Maven 動作確認
mvn -v

# プロジェクトビルド
cd ~/Projects/Java_kakeibo   # ← 実際のパスに変更
mvn clean package

echo "完了！ target/kakeibo-0.0.1-SNAPSHOT.jar が生成されました。"
