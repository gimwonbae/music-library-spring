FROM openjdk:11.0-jdk
VOLUME /tmp
WORKDIR /app
COPY ./build/libs/*.jar mwl.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom","-jar", "mwl.jar"]