# Institution service

A Spring Boot service for managing institutions providing secure CRUD operations with JWT authentication and authorization.

## Technologies

This project is developed using Java with Spring Boot framework. Key components and technologies employed include:

* Database: Utilizes an H2 embedded database within the runtime environment.
* Security: Implements Spring Security Core for robust JWT-based authentication and authorization. Additionally, a rate limiter is integrated into the
  JWT filter to enhance security measures.
* Logging: Utilizes Log4j2 for comprehensive logging capabilities, including console logging and rolling file appender configuration.
* Monitoring: Integrates Spring Actuator and Prometheus to facilitate monitoring of application metrics. Custom metrics are also incorporated for more
  detailed insights into application performance.
* ORM: Implements Hibernate ORM for efficient entity mapping and performing CRUD operations.
* API Documentation: Generates API documentation using Springdoc OpenAPI for improved documentation and ease of use.
* Testing: Utilizes JUnit and Mockito for effective unit testing to ensure code reliability and quality.
* Aspect-Oriented Programming (AOP): Implements AOP to log requests and responses for enhanced monitoring and debugging capabilities.

<img src="https://img.shields.io/badge/Language-Java-orange.svg">

## Usage

To start the application, begin by cloning the repository using the command "git clone https://github.com/PatrickB1994/institution-service.git". Next,
navigate to the project directory by running "cd institution-service". Then, build the project and initiate the application with the command "mvn
spring-boot:run". You can access the API Documentation via http://localhost:8080/swagger-ui/index.html#/. To utilize the provided service,
authentication is required, requiring the acquisition of a JWT token. Utilize the provided authentication endpoints to log in with the default
credentials: username "admin" and password "admin". Upon successful authentication, you will receive a token which should be included in the
Authorization header for subsequent requests. With a valid JWT token, you are now able to perform CRUD operations. Additionally, the application
generates logs which can be monitored in the /logs directory.

## Author

- [Patrick Badran, Senior Developer](https://github.com/PatrickB1994)

## Copyright & Licensing Information

[License](https://www.apache.org/licenses/LICENSE-2.0.txt)
