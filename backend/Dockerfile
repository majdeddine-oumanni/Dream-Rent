FROM openjdk:21
#the version for the application that the application will run on the container
VOLUME /tmp
#creation d'un repertoire
COPY target/backend-0.0.1-SNAPSHOT.jar app.jar
#
ENTRYPOINT ["java", "-jar", "/app.jar"]