**README.md**

# CRUD Operation Application

This repository contains a CRUD (Create, Read, Update, Delete) operation application developed using Spring Boot. The application allows users to perform basic CRUD operations on user data stored in a MySQL database.

## Setup Instructions

### Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK)
- IntelliJ IDEA (or any preferred IDE)
- MySQL Database Server
- Postman (for testing APIs)
- XAMPP (for managing MySQL server locally)

### Database Configuration

1. Open XAMPP Control Panel.
2. Start Apache and MySQL services if they are not already running.
3. Open phpMyAdmin from the XAMPP Control Panel.
4. Create a new database named `cruddb` if it does not exist already.

### IDE Setup

1. Clone this repository to your local machine.
2. Open IntelliJ IDEA.
3. Import the project by selecting the root directory of the cloned repository.
4. Wait for IntelliJ to import the project and resolve dependencies.

### Application Configuration

1. Navigate to the `application.properties` file located in `src/main/resources`.
2. Configure the following properties according to your MySQL setup:
    - `spring.datasource.url`: URL of your MySQL database server. Change `localhost:3306` if your MySQL server is running on a different host or port.
    - `spring.datasource.username`: MySQL username (default is `root`).
    - `spring.datasource.password`: MySQL password (leave empty if no password is set).
    - `spring.datasource.driver-class-name`: MySQL driver class name.
    - `spring.jpa.hibernate.ddl-auto`: Hibernate DDL auto setting. Change to `create` if you want Hibernate to create the database schema, or `validate` to only validate the schema.
    - `spring.jpa.show-sql`: Whether to show SQL queries in the console (`true` or `false`).

### Running the Application

1. Run the Spring Boot application from IntelliJ IDEA.
2. Once the application is running, it should be accessible at `http://localhost:8080`.

### Testing the APIs

1. Open Postman.
2. Use the provided API endpoints (`GET`, `POST`, `PUT`, `DELETE`) to interact with the application and perform CRUD operations on user data.
