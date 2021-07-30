# Movie Characters API

## Assignment

The assignment was to create a PostGreSQL database using Hibernate and later expose it through a Web API in Spring Web. The content of the assignment was about different movies and characters, as well as different types of franchises and how these interact with each other. Furthermore, this task was performed through pair programming with equal contribution. You can read about the assignment [here](Assignment 3_Java_Web API creation with Hibernate.pdf).

## Project structure

The project structure involves three different entities in Movie, Character and Franchise. Hibernate has been used to provide an object-relation-mapping for these entities. The central part in the structure is between three different packages which includes model representation for the PostGreSQL database, controller classes for CRUD operations and lastly repository interfaces. A databaseseeder are following with some dummy data, this can be changed in DatabaseSeeder.class

## Documentation
Swagger API has been included to the project to handle API documentation. Following are also a postman_collection file with calls to the API endpoints. [Swagger URL](http://localhost:8080/swagger-ui.html)

## Members
**Joakim Österberg & Marcus Thornemo Larsson**
