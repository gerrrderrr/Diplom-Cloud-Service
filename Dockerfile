FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8081
COPY target/CloudService-0.0.1-SNAPSHOT.jar cloud.jar
ENTRYPOINT ["java", "-jar", "cloud.jar"]