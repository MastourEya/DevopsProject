FROM openjdk:8
COPY target/DevOps_Project-1.0.jar .
EXPOSE 8082
ENTRYPOINT ["java","-jar","DevOps_Project-1.0.jar"]
