FROM node:22-bookworm-slim AS frontend-build

WORKDIR /frontend

COPY finflow-angular/package*.json ./
RUN npm ci

COPY finflow-angular/ ./
RUN npm run build

FROM maven:3.9.9-eclipse-temurin-17 AS backend-build

WORKDIR /workspace

COPY . .
COPY --from=frontend-build /frontend/dist/finflow-angular/browser/ ./src/main/resources/static/

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=backend-build /workspace/target/finflow-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
