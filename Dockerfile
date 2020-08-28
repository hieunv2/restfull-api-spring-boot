FROM openjdk:11.0.8-jre-slim

WORKDIR /app

COPY target/api-exec.jar api.jar

EXPOSE 8080

CMD /app/api.jar
