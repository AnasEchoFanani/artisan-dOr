# Artisan d'Or - Backend

Welcome to the backend repository for the Artisan d'Or application, built using Spring Boot.

### Table of Contents

1. [Introduction](#introduction) 
2. [Features](#features) 
3. [Technologies](#technologies) 
4. [Getting Started](#getting-started) 
5. [Configuration](#configuration) 
6. [Running the Application](#running-the-application) 
7. [API Documentation](#api-documentation) 
8. [License](#license)

## Introduction

Artisan d'Or is an application designed to connect artisans with customers seeking handcrafted goods and services. This repository contains the backend codebase, which provides the necessary APIs and business logic to support the application.

## Features

- User authentication and authorization 
- Artisan profile management 
- Product and service listings 
- Order processing and management 
- Review and rating system 
- Notification system

## Technologies

- **Spring Boot** - Framework for building Java-based applications 
- **Spring Security** - For authentication and authorization 
- **Hibernate** - ORM for database interactions 
- **PostgreSQL** - Database management system 
- **Swagger** - API documentation 
- **Maven** - Build and dependency management

## Getting Started

### Prerequisites

- Java 11 or higher 
- Maven 3.6+ 
- PostgreSQL database

### Installation

- **Clone the repository**
  
  ```bash
  git clone https://github.com/your-username/artisan-dor-backend.git
  cd artisan-dor-backend
  ```

- **Set up the database**
  
  - Create a new PostgreSQL database named `artisan_dor`.
  
  - Update the database configuration in `src/main/resources/application.properties`.
    
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/artisan_dor
    spring.datasource.username=your-username
    spring.datasource.password=your-password
    spring.jpa.hibernate.ddl-auto=update
    ```

- **Build the project**
  
  ```bash
  mvn clean install
  ```

## Configuration

Configuration settings are managed in `application.properties` and `application.yml`. Key configurations include:

- Database connection settings
- Server port
- Security settings

## Running the Application

To run the application, execute the following command:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Documentation

API documentation is provided by Swagger. Once the application is running, you can access the Swagger UI at:

```bash
http://localhost:8080/swagger-ui.html
```

## License

This project is copyrighted under © Artisan d'Or. All rights reserved.
