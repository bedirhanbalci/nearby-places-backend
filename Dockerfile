FROM openjdk:21-jdk-slim AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim
COPY --from=build /app/target/*.jar demo.jar
RUN mkdir /uploads && chmod -R a+rw /uploads
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]