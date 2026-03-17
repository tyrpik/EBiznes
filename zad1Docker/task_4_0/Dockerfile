FROM ubuntu:24.04
# Sprawdziłam, że istnieje już java w wersji 8 dla ubuntu 24.04: https://packages.ubuntu.com/search?keywords=openjdk-8-jdk&searchon=names&suite=noble&section=all
# Obecna najnowsza wersja Gradle to 9.4.0 z 04.03.2026: https://gradle.org/releases/
ARG GRADLE_VERSION=9.4.0
# Wybiera opcje domyślne, nie wyświetla okienek
ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y openjdk-8-jdk openjdk-17-jdk kotlin curl unzip && rm -rf /var/lib/apt/lists/*
RUN curl -L https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle.zip && unzip gradle.zip -d /opt && rm gradle.zip

ENV PATH="${PATH}:/opt/gradle-${GRADLE_VERSION}/bin"
# Ustawiamy Javę 17 jako domyślną dla systemu (żeby Gradle działał)
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

WORKDIR /app
COPY build.gradle /app/

CMD ["sh", "-c", "echo 'Domyslna Java (dla Gradle):' && java -version && echo '\nJava 8 tez jest dostepna:' && /usr/lib/jvm/java-8-openjdk-amd64/bin/java -version && echo '\nSprawdzamy dependencies:' && gradle dependencies"]