# Backend for Libraries with JWT Authentication and Role Authorization - Java / Spring Boot

This project is a backend for a library system. It offers features such as user authentication and authorization using JWT tokens (JSON Web Tokens) and a role system. The backend is built in Java / Spring Boot with the PostgreSQL database.

## Feature Examples

- **User authentication**: Login and user registration system.
- **Authorization via JWT**: After logging in, users receive a JWT token for subsequent access.
- **Library Management**: Functionalities for adding, viewing, editing and deleting book items, managing loans and controlling book reservations.
- **Unit tests**: Unit tests of all the controllers, repositories and user-facing services in the application.

## Technologies Used

- **Java / Spring Boot**: Server execution environment.
- **PostgreSQL**: Efficient database for storing user data and library information.
- **JWT (JSON Web Tokens)**: Used for authenticating and authorizing users based on roles.
- **JUnit4 / Mockito**: Used for the unit testing of users.

## API documentation

The full API documentation is available on the `/swagger-ui/index.html` endpoint. The documentation is interactive and allows you to test the endpoints directly via the Swagger interface.

## Installation and Use Instructions

1. Clone the repository: `git clone [REPOSITORY_URL]`

2. Navigate to the project folder and install the dependencies: `cd [PROJECT_FOLDER_NAME]` and then run `mvn install` to generate the dependency update and the application JAR (if desired).

3. **Install PostgreSQL 14** (Optional):
   - Download PostgreSQL 14 from the official website: [Download PostgreSQL](https://www.postgresql.org/download/).
   - Follow the installation instructions for your operating system.
   - During installation, set `postgres` as the username and `admin` as the password for the PostgreSQL superuser.
   - Create a new database called `books`.

4. Start the server: run the application from its main class in your preferred IDE (I recommend Intellij IDEA Community or Ultimate).

5. Go to `http://localhost:8080/swagger-ui/index.html` in your browser to view the API documentation.

---

Developed with ❤️ by Samuel Baldasso
