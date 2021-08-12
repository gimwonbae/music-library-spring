FROM openjdk:11-jre-slim
WORKDIR /app
COPY ./build/libs/*.jar mwl.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","mwl.jar"]