FROM openjdk:11
EXPOSE 8082
ARG JAR_FILE=client-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]