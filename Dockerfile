# Etapa 1: construir usando Maven + JDK 17 (válido para compilación)
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .

# Compilar sin tests
RUN mvn clean package -DskipTests

# Etapa 2: ejecutar con OpenJDK 23
FROM openjdk:23

WORKDIR /app

COPY --from=build /app/target/uniCosmet.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


