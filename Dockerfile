FROM openjdk:21-jdk
WORKDIR /app
ENV PROJECT_NAME institution-service
COPY target/*.jar ${PROJECT_NAME}.jar
RUN mkdir /logs
EXPOSE 8080
CMD java -jar /app/${PROJECT_NAME}.jar