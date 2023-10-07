# Movie CRUD Application

A simple Spring Boot mini-project that demonstrates CRUD operations for managing movie data, designed as a project for PT. Xsis Mitra Utama Recruitment Technical Test.

![Java Version](https://img.shields.io/badge/java-17-brightgreen)

## Table of Contents
- [Overview](#overview)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Endpoints](#endpoints)
- [Database](#database)
- [Usage](#usage)

## Overview

This Spring Boot project provides a RESTful API for managing movie records. It allows you to perform the following operations:
- Create a new movie
- Retrieve a list of all movies
- Retrieve a specific movie by ID
- Update an existing movie
- Delete a movie by ID

## Getting Started

### Prerequisites

To run this project, you need to have the following prerequisites installed:

- Java Development Kit (JDK) 8 or higher
- Maven 3.x

### Installation

1. Clone the repository:

   ```shell
   git clone https://github.com/bayuaaji/movieapp.git

2. Navigate to the project directory:

   ```shell
    cd movie-app

3. Build and run the application:

   ```shell
    mvn spring-boot:run


## Endpoint

(path = "/api/v1")

- POST /Movies: Create a new movie.
- GET /Movies: Retrieve all movies.
- GET /Movies/{id}: Retrieve a specific movie by ID.
- PUT /Movies/{id}: Update an existing movie by ID.
- PATCH /Movies/{id}: Patch partial attributes of an existing movie by ID.
- DELETE /Movies/{id}: Delete a movie by ID.

## Database

This project uses MYSQL database to store movie data.
You can customize the database configuration in the application.properties file.


## Usage
You can interact with the movie CRUD API using tools like curl, Postman, or your preferred REST client.
Here are sample API request to create new movie record, and patch or update that existing movie:

   ```shell
    curl -X POST -H "Content-Type: application/json" -d '{
        "title":"Pengabdi Setan 2 Comunion",
        "description":"Adalah sebuah film horor Indonesia tahun 2022 yang disutradarai dan ditulis oleh Joko Anwar sebagai sekuel dari film tahun 2017, Pengabdi Setan.",
        "rating":7,
        "image":""
    }' http://localhost:8885/api/v1/Movies
    
    curl -i -X PATCH -H "Content-Type: application/json-patch+json" -d '[
        {"op":"replace","path":"/rating","value":8},
        {"op":"replace","path":"/description","value":"Pengabdi Setan 2 adalah sebuah film horor Indonesia tahun 2022 yang disutradarai dan ditulis oleh Joko Anwar sebagai sekuel dari film tahun 2017, Pengabdi Setan"}
    ]' http://localhost:8885/api/v1/Movies/1
