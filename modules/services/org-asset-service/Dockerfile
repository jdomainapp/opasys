FROM busybox:1.35.0-uclibc as busybox
FROM gcr.io/distroless/java17-debian11
COPY --from=busybox /bin/sh /bin/sh
COPY --from=busybox /bin/mkdir /bin/mkdir
ENTRYPOINT ["/bin/sh", "/entrypoint.sh"]

WORKDIR /project
COPY target/org-asset-service-0.0.1-SNAPSHOT.jar app.jar
RUN mkdir "opasys_storage_temp"
ENTRYPOINT ["java", "-jar", "app.jar"]