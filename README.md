# Spring Boot backend REST API Project

This is a sample Java / Maven / Spring Boot application that REST API implementation for below scenarios - 
1. API to get account(s) for the user
2. API to provide the list of transactions for given account number
 
Rest API endpoints
========================================================================================================================================
API Method #1  
Name: getAccounts( API to get account details for user) 
Request Type: GET
Request URL: http://localhost:8081/v1/account/{userId}
Sample URL : http://localhost:8081/v1/account/anztest

Optional request parameters are :
Page number
Page Size

Other Sample URLs :
http://localhost:8081/v1/account/anztest?page=0
http://localhost:8081/v1/account/anztest?page=0&size=10

API Method #2  
Name: getTransctions( API to get transaction details for account) 
Request Type: GET
Request URL: http://localhost:8080/v1/account/{accountNumber}/transactions
Sample URL : http://localhost:8081/v1/account/100002/transactions

Optional request parameters are :
Page number
Page Size

Other Sample URLs :
http://localhost:8081/v1/account/100002/transactions?page=0
http://localhost:8081/v1/account/100002/transactions?page=0&size=10

Build / Deployment
========================================================================================================================================

1. Maven is used to build the project.

2. Deployment is done using Spring Boot's embedded Tomcat Server.

Please follow the below steps to build and run this project in your local machine.

    Project can be cloned from the repository by executing following command in local machine using GIT tools: git clone xxxxxxx
    Run 'mvn clean package' from project root directory.
    Run the project using 'mvn spring-boot:run'.

Prerequisites/ Installation
========================================================================================================================================
Following tools/plugins/technologies are required to implement, build and run this project.

    Java 1.8
    Spring Boot 2.1.5.RELEASE
	Swagger 2.9.2
	H2 DB
	JSON
	Lombok
    Mockito API
    Unit test using JUnit
    JUnit code coverage using Jacoco
    Embedded Tomcat
    REST API using JSON request/response
    IntelliJ IDEA
    Github repository
    Windows 10

You need to install, configure and setup above in your system before build.


Design Specification
========================================================================================================================================
This provided Spring boot Rest implementation.
It is designed by making use of microservice design patterns such as decomposition, Database per service.
This can be expanded using API gateway and aggregator patterns.
The security features can be added by placing appropriate jks(key) file in keystore folder under resources.   

Running test(s)
=======================================================================================================================================

1. All unit test cases have been covered using Mockito/Junit API with 85% code coverage. Jacoco report generated can be generated by using command mvn clean package. The report is generated  in the target folder @AccountService\target\jacoco-ut. View the index.html file to see the code coverage details.

2. Application can be tested using in-built Swagger plugin.

    URL : http://localhost:8081/swagger-ui.html

3. The application has been built with the following assumption(s):

   a. Account number is unique for the user.

   b. For creating account and transaction dataset, H2 in-memory database is used which loads the data on application start-up. 
    Refer sql files for table creation script & sample data script: 
    
       AccountService_DDL_1.0.0.0.sql, AccountService_DML_1.0.0.0.sql
       
    H2 Database console can be accessed using below URL in browser :
    
      http://localhost:8081/h2
      
        jdbc url = jdbc:h2:mem:accountdb, username = sa, password =
