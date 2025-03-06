FROM amazoncorretto:11
EXPOSE 8888
ARG JAR_FILE=server-client-film-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]