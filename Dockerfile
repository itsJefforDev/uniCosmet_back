# Etapa 1: Construcción con Maven
FROM maven:3.9.5-eclipse-temurin-23 AS build

# Copia el proyecto
WORKDIR /app
COPY . .

# Compila el proyecto y genera el .jar
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con solo el JDK y el JAR
FROM openjdk:23

WORKDIR /app

# Copiamos el .jar desde la etapa de construcción
COPY --from=build /app/target/uniCosmet.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

