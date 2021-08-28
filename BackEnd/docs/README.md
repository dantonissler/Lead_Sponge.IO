# LeadSponge.IO

Run with Docker

Prepare Dockerfile for Java/Spring Boot application and docker-compose.yml for MySQL Server

Type the below command at the project root directory, make sure your local Docker engine is running

docker-compose up


Run with Maven
You can run the app with your local MySQL Server by updating "hk-mysql" on application.properties to "localhost" and type the below command at the project root directory

mvn clean spring-boot:run

Testing time

Access to localhost:8080 and start playing around with the app


