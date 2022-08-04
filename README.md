# Project Guidelines

## Introduction

Your task is to build an API for a book list app. A user should be able to view
books and add them to different reading lists.

## Project Setup

Use [Spring Initializr](https://start.spring.io/) to create a project with the
following properties:

- Java 11
- Maven Build

And add the following dependencies:

- Spring Web.
- Spring Data JPA.
- A database of your choice (H2, Postgres, MySQL).

Generate the project and open it up in IntelliJ.

## Project Requirements

Here is a diagram of the tables you will have to implement. Note that the join
tables are not listed in the diagram.

**IMPORTANT:** `User` is a reserved keyword in SQL so Hibernate won’t be able to
create a table unless you escape the table name. Check out
[this article](https://vladmihalcea.com/escape-sql-reserved-keywords-jpa-hibernate/)
for different ways of handling this issue.

![Database relationship diagram](https://curriculum-content.s3.amazonaws.com/java-spring-1/db-diagram-spring-2-project.png)

The relationships displayed are as follows:

- A user can have many reading lists.
- A reading list belongs to a user.
- A reading list can have many books.
- A book can belong to many reading lists.
- A book can have many genres.
- A genre can belong to many books.
- An author can have many books.
- A book can belong to a single author.

### Required Objectives

| Endpoint                                    | Description                                               |
| ------------------------------------------- | --------------------------------------------------------- |
| GET /api/books                              | Gets all the books in the Book table.                     |
| GET /api/books/{id}                         | Gets a single book with the given ID.                     |
| POST /api/books                             | Create a book.                                            |
| PUT /api/books                              | Update a book.                                            |
| DELETE /api/books/{id}                      | Delete a book.                                            |
| GET /api/genre/{id}/books                   | Get all books in the genre with the given ID.             |
| POST api/users                              | Create a user.                                            |
| DELETE api/users/{id}                       | Delete a user.                                            |
| GET /api/users/{id}/reading_lists           | Gets the given user’s reading lists.                      |
| POST /api/users/{id}/reading_lists          | Create a new reading list for the user with the given ID. |
| GET /api/users/{id}/reading_lists/{list_id} | Gets the given user’s reading list with the ID list_id.   |

Implement the endpoints given in the table:

- Add validations (models, controller, parameters).
- Handle exceptions.
- Send back appropriate status codes for each response.

## Stretch Objectives

- Authenticate the user and ensure that a user can only access their reading
  list.
- Create an endpoint for searching a book by its `title`.
- Add tests for endpoints.
- Add logging.

## Submission

Make sure your project root is a Git repository. Once you have completed the
objectives, push the changes to your repo and submit the link on Canvas.
