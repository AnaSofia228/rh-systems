# Etapa 1: Construcción de la aplicación
FROM maven:3-openjdk-17 AS build

# Definir el directorio de trabajo
WORKDIR /app

# Copiar el archivo de dependencias
COPY pom.xml .

# Descargar las dependencias anticipadamente
RUN mvn dependency:go-offline -B

# Copiar el código fuente de la aplicación
COPY src ./src

# Compilar la aplicación y crear el .jar
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final (ligera)
FROM openjdk:17-jdk-slim

# Definir el directorio de trabajo
WORKDIR /app

# Copiar el archivo .jar desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Configurar la exposición del puerto donde funciona la aplicación
EXPOSE 8761

# Comando de inicio del contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]