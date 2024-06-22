# Web Application Project

This is a basic web application written in Java. The primary focus of this project is to understand the basics of HTTP requests and request handling using JSP and Servlets.

## Project Overview

This project demonstrates the fundamentals of handling HTTP requests and responses. The code is structured to emphasize learning over best practices, hence it may not follow the conventional structure of a web application.

## Features

- Handle HTTP GET and POST requests
- Basic CRUD operations for managing books
- Session management to log search activities
- Cookie implementation to store the last search term
- Basic data validation and error handling
- JSP pages for different views (e.g., book overview, add book, update book, search results)

## Technologies Used

- **Java**
- **HTML/CSS**
- **JSP (JavaServer Pages):** Used for creating dynamic web pages.
- **Servlets:** Java classes that handle HTTP requests and responses.
- **Apache Tomcat:** A web server and servlet container used to deploy and run the web application.
- **JDBC** For database connectivity (not explicitly mentioned, but commonly used in such projects).

## Deployment

The project is launched in a Tomcat container. Below are the steps to deploy the application:

1. Install Apache Tomcat (if not already installed).
2. Build the project using your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
3. Deploy the generated WAR file to the Tomcat `webapps` directory.
4. Start the Tomcat server.
5. Access the application via `http://localhost:8080/(WAR file name when started locally)`.

## Acknowledgements

This project is created for educational purposes to understand the basics of web application development using Java.
