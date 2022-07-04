FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} lodging-service.jar

ENTRYPOINT ["java","-jar","lodging-service.jar"]