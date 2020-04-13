FROM ubuntu:18.04

MAINTAINER me

RUN apt update
RUN apt install -y git
RUN apt install -y openjdk-11-jre
RUN export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
RUN export PATH=$PATH:$JAVA_HOME/bin
RUN apt install -y maven

WORKDIR /

RUN git clone https://github.com/zuber966/spring-restaurant-app.git

EXPOSE 8083
#3306 8990 33060

WORKDIR /spring-restaurant-app/authentication-service

RUN mvn install

WORKDIR /spring-restaurant-app/authentication-service/target

#WORKDIR /

#COPY target/authentication-service-0.0.1-SNAPSHOT.jar /

CMD ["java", "-jar", "authentication-service-0.0.1-SNAPSHOT.jar"]

