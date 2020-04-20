FROM openjdk:11
EXPOSE 8990
WORKDIR /
COPY target/eureka.jar /eureka.jar
ENTRYPOINT ["java", "-jar", "eureka.jar"]