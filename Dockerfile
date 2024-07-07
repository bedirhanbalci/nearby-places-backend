FROM openjdk:21-jdk-slim AS build
COPY .. .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build target/*.jar demo.jar
RUN mkdir /uploads && chmod -R a+rw /uploads
EXPOSE 8070
CMD ["java", "-jar", "demo.jar"]