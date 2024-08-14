# Etapa de construcción
# [builder 1/13]
FROM openjdk:17-alpine as builder

# Se establece directorio de trabajo
# [builder 2/13]
WORKDIR /app/quarkus-utilities-exaples

# Se copia pom a base directory
# [builder 3/13]
COPY ./pom.xml .

# Se instala maven
# [builder 4/13]
RUN apk --no-cache add maven

# Se depuran dependencias
# [builder 5/13]
RUN mvn dependency:purge-local-repository

# Compilación sin ejecutar las pruebas
# [builder 6/13]
RUN mvn clean package -Dmaven.test.skip=true -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

# Se copia src
# [builder 7/13]
COPY ./src/ ./src

# Compilación final
# [builder 8/13]
RUN mvn clean package -Dmaven.test.skip=true

# Etapa de construcción final
# [builder 9/13]
FROM openjdk:17-jdk-alpine

# [builder 10/13]
# Se establece directorio de trabajo
WORKDIR /app

# [builder 11/13]
# Se copia Jar e imagen builder
COPY --from=builder /app/quarkus-utilities-exaples/target/app.jar .

# [builder 12/13]
# Se declara el puerto
EXPOSE 8080

# [builder 13/13]
# Se ejecuta el jar
ENTRYPOINT ["java", "-jar", "app.jar"]