# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY backend/pom.xml backend/pom.xml
COPY backend/src backend/src
COPY frontend frontend
WORKDIR /app/backend
# -Pprod also builds the React/Vite frontend (via frontend-maven-plugin,
# which downloads its own Node - the base image doesn't need Node
# installed) and bundles it into the jar's static resources.
RUN mvn clean package -DskipTests -Pprod

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/backend/target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]