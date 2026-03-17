FROM ubuntu:24.04
# Sprawdziłam, że istnieje już java w wersji 8 dla ubuntu 24.04: https://packages.ubuntu.com/search?keywords=openjdk-8-jdk&searchon=names&suite=noble&section=all

# Wybiera opcje domyślne, nie wyświetla okienek
ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y openjdk-8-jdk kotlin curl unzip && rm -rf /var/lib/apt/lists/*

CMD ["sh", "-c", "java -version && kotlinc -version"]