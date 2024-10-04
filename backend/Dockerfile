# Usando a imagem base do OpenJDK para Java 21
FROM openjdk:21-jdk-slim

# Definindo diretório de trabalho
WORKDIR /app

# Copiando o JAR do backend
COPY build/libs/demo-0.0.1-SNAPSHOT.jar /app/backend.jar

# Expondo a porta do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "backend.jar"]
