FROM openjdk:11

MAINTAINER me

WORKDIR /

RUN apt install -y git
RUN mvn -version

COPY target/authentication-service-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8083
ENV EUREKA_CLIENT_SERVICEURL_DEFAULTZONE: http://eureka:8990/eureka

ENTRYPOINT ["java", "-jar", "app.jar"]

