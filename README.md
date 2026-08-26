# REST Assured API Automation Framework

API automation framework built using Java, REST Assured, Cucumber BDD, TestNG and Maven.

## Tech Stack

- Java
- REST Assured
- Cucumber BDD
- TestNG
- Maven
- Java Faker
- Jackson
- Hamcrest

## Framework Features

- REST API automation using REST Assured
- BDD implementation using Cucumber
- POJO-based request payloads
- Dynamic test data generation using Java Faker
- Request and response specification builders
- Path parameter handling
- Query parameter handling
- JSON request/response validation
- HTTP status code validation
- Response body validation
- CRUD API automation
- Reusable utility class

## API Operations Covered

| Operation | HTTP Method |
|---|---|
| Create User | POST |
| Retrieve User | GET |
| Update User | PUT |
| Delete User | DELETE |

## Project Structure

src
├── test
│   ├── java
│   │   ├── steps
│   │   ├── userPojo
│   │   └── utils
│   └── resources
│       └── user.feature

## How to Run

Clone the repository:

git clone <repository-url>

Navigate to the project:

cd rest-assured-cucumber-api-automation

Run tests:

mvn test

## Author :

Omkar Pujari
