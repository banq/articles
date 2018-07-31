FROM openjdk:latest
EXPOSE 8080
RUN mkdir /opt/application
COPY ./build/libs/gs-spring-boot-0.1.0.jar /opt/application
CMD ["java","-jar","/opt/application/gs-spring-boot-0.1.0.jar"]