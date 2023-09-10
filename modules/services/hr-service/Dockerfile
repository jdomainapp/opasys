FROM gcr.io/distroless/java17-debian11
WORKDIR /project
COPY target/hr-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]