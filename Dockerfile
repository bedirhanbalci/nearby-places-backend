FROM maven:3.8.6-openjdk-21-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /app/target/*.jar demo.jar
RUN mkdir /uploads && chmod -R a+rw /uploads
EXPOSE 8070
CMD ["java", "-jar", "demo.jar"]