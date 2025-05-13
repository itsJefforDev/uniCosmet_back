# Usa OpenJDK 23 desde Docker Hub
FROM openjdk:23

# Define el directorio de trabajo
WORKDIR /app

# Copia el JAR de la aplicación (ajusta si es necesario)
COPY target/uniCosmet.jar app.jar

# Expón el puerto 8080
EXPOSE 8080

# Ejecuta la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
