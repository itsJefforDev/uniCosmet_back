FROM openjdk:23

WORKDIR /app

COPY target/uniCosmet.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]



