# 📖 Minimalist Book Manager API

## Introduction
This is the starter repository for the Further APIs session. It provides a start to creating a Minimalist Book Manager API
using a Test-Driven Development approach.

### Pre-Requisites
- Java SE Development Kit 17
- Maven

### Technologies & Dependencies
- Spring Boot
- Spring Web
- H2 Database
- Lombok
- Spring Data JPA

### How to Get Started
- Fork this repo to your Github and then clone the forked version of this repo

### Main Entry Point
- The Main Entry Point for the application is: [BookmanagerApplication.java](src/main/java/com/techreturners/bookmanager/BookmanagerApplication.java)

### Running the Unit Tests
- You can run the unit tests in IntelliJ, or you can go to your terminal and inside the root of this directory, run:

`mvn test`

### Tasks

Here are some tasks for you to work on:

📘 Discussion Task

Explore the code and make notes on the following features and how it is being implemented in the code. We'd like you to note down what classes and methods are used and how the objects interact.

The features are:
- Get All Books
* When user makes a GET requests /api/v1/book, the bookManagerController.getAllBooks is invoked. 
* Inside this method, it calls the bookManagerService.getAllBooks method. 
* (bookManagerService is auto instantiated by Spring boot with dependency injection. )
* Inside this method, it calls the bookManagerRepository.findAll()
* (bookManagerRepository is auto instantiated by Spring boot with dependency injection. )
* bookManagerRepository then call the model Book to query the database table Book. 
* Database then return the book info to the bookManagerRepository.
* bookManagerRepository then return the book info back to bookManagerService.
* bookManagerService then return the book info to bookManagerController. 
* bookManagerController return the response to the user.

- Get a Book by ID
* When user makes a GET requests /api/v1/book/1, the bookManagerController.getBookById is invoked.
* Inside this method, it calls the bookManagerService.getBookById method.
* (bookManagerService is auto instantiated by Spring boot with dependency injection. )
* Inside this method, it calls the bookManagerRepository.findById()
* (bookManagerRepository is auto instantiated by Spring boot with dependency injection. )
* bookManagerRepository then call the model Book to query the database table Book. 
* Database then return the book info to the bookManagerRepository.
* bookManagerRepository then return the book info back to bookManagerService.
* bookManagerService then return the book info to bookManagerController.
* bookManagerController return the response to the user.

- Add a Book
* When user makes a POST requests /api/v1/book with request body book, the bookManagerController.addBook is invoked.
* Inside this method, it calls the bookManagerService.insertBook method.
* (bookManagerService is auto instantiated by Spring boot with dependency injection. )
* Inside this method, it calls the bookManagerRepository.save()
* (bookManagerRepository is auto instantiated by Spring boot with dependency injection. )
* bookManagerRepository then call the model Book to save the database table Book. 
* Database then return the book info which is saved to the bookManagerRepository.
* bookManagerRepository then return the book info back to bookManagerService.
* bookManagerService then return the book info to bookManagerController.
* bookManagerController return the response to the user.

- Update a Book
* When user makes a PUT requests /api/v1/book with request body book, the bookManagerController.updateBookById is invoked.
* Inside this method, it calls the bookManagerService.updateBookById method.
* (bookManagerService is auto instantiated by Spring boot with dependency injection. )
* Inside this method, it calls the bookManagerRepository.findById()
* (bookManagerRepository is auto instantiated by Spring boot with dependency injection. )
* bookManagerRepository then call the model Book to query the database table Book. 
* Database then return the book info if found to the bookManagerRepository.
* bookManagerRepository then return the book info back to bookManagerService.
* bookManagerService then base on the result to set the book attributes based on user input
* bookManagerService then call bookManagerRepository.save()
* bookManagerRepository then call the model Book to save the database table Book. 
* Database then return the book info which is saved to the bookManagerRepository.
* bookManagerRepository then return the book info back to bookManagerService.
* bookManagerService then return the book info to bookManagerController.
* bookManagerController return the response to the user.

📘 Task 1: Implement the following User Story with tests.

`User Story: As a user, I want to use the Book Manager API to delete a book using its ID`


📘 Extension Task: Oh no! 😭 We've only covered the happy paths in the solution, can you figure out a way
to add in exception handling to the project? 

- Clue 1: What if someone wants to add a book with an ID for a book that already exists? How do we handle this gracefully?


- Clue 2: What if someone wants to find a book by an ID that doesn't yet exist? 
  How can we improve the API by handling errors gracefully and show a helpful message to the client?



## Notes:
Backup postman collection to github procedure:
https://learning.postman.com/docs/integrations/available-integrations/github/
  
