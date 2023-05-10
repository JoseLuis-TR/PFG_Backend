FROM openjdk:11-jdk-slim
#VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app.jar","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv6Addresses=false"]
