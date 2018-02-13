FROM openjdk:8-jre
ADD target/VinSubsciber-1.0-SNAPSHOT.jar app.jar
ADD config.yml app-config.yml
EXPOSE 8001
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar", "server", "app-config.yml"]