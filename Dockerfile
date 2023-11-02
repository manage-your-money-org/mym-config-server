FROM eclipse-temurin:17-jdk-alpine
COPY build/libs/*.jar mym-config-server.jar
ENTRYPOINT ["java", "-jar", "/mym-config-server.jar"]