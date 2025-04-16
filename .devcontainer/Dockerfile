# ベースイメージとして Ubuntu 20.04 を使用
FROM ubuntu:20.04

# 非対話モードでの環境変数設定
ENV DEBIAN_FRONTEND=noninteractive

# 必要なパッケージのインストールと OpenJDK 11 のインストール
RUN apt-get update && \
    apt-get install -y software-properties-common wget gnupg git && \
    add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update && \
    apt-get install -y openjdk-11-jdk && \
    apt-get clean

# Javaバージョン確認用
RUN java -version

# 環境変数設定
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
ENV PATH="$JAVA_HOME/bin:$PATH"

# 作業ディレクトリ
WORKDIR /app
