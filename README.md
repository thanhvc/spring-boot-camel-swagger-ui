# spring-boot-camel-swagger-ui

This repository contains a demo of Spring Boot with Apache Camel and Swagger UI.

* Spring Boot 2.2.5
* Apache Camel 2.25.4
* Swagger UI 2.2.8
* Hawtio 2.17.7

Build code:
   
   mvn clean install
   
Run application
   
    java -jar target/spring-boot-swagger-0.0.1-SNAPSHOT.jar

**swagger doc:** http://localhost:8080/swagger/index.html

    http://localhost:8080/api/person

    java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=127.0.0.1,suspend=y,address=9000

**Integrate hawtio:**

    http://localhost:8080/actuator/hawtio/
  
  **config in application.properties:** management.endpoints.web.base-path = /
  
    http://localhost:8080/hawtio
