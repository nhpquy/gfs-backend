# GFS Backend

[![Java](https://img.shields.io/badge/Java-8-blue)](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)

## Description
GFS Backend is a Java-based backend service designed to provide APIs for various educational management operations, including managing students, courses, and enrollments.

## Features
- Student Management
- Course Management
- Enrollment Management
- RESTful APIs for CRUD operations
- User authentication and authorization

## Installation
To get started with the GFS Backend, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/nhpquy/gfs-backend.git
    cd gfs-backend
    ```

2. **Build the project:**
   Ensure you have [Java 8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) and [Maven](https://maven.apache.org/download.cgi) installed.
    ```bash
    mvn clean install
    ```

3. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

## API Endpoints

### Student Management

- **Create Student**
    ```
    POST /api/students
    ```

  **Request Body:**
    ```json
    {
        "name": "John Doe",
        "email": "john.doe@example.com",
        "course": "Computer Science"
    }
    ```

  **Response:**
    ```json
    {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com",
        "course": "Computer Science",
        "created_at": "2025-01-16T02:49:43Z"
    }
    ```

- **Get All Students**
    ```
    GET /api/students
    ```

  **Response:**
    ```json
    [
        {
            "id": 1,
            "name": "John Doe",
            "email": "john.doe@example.com",
            "course": "Computer Science",
            "created_at": "2025-01-16T02:49:43Z"
        },
        ...
    ]
    ```

- **Get Student by ID**
    ```
    GET /api/students/{id}
    ```

  **Response:**
    ```json
    {
        "id": 1,
        "name": "John Doe",
        "email": "john.doe@example.com",
        "course": "Computer Science",
        "created_at": "2025-01-16T02:49:43Z"
    }
    ```

- **Update Student**
    ```
    PUT /api/students/{id}
    ```

  **Request Body:**
    ```json
    {
        "name": "John Doe Updated",
        "email": "john.doe.updated@example.com",
        "course": "Data Science"
    }
    ```

  **Response:**
    ```json
    {
        "id": 1,
        "name": "John Doe Updated",
        "email": "john.doe.updated@example.com",
        "course": "Data Science",
        "updated_at": "2025-01-16T02:49:43Z"
    }
    ```

- **Delete Student**
    ```
    DELETE /api/students/{id}
    ```

  **Response:**
    ```json
    {
        "message": "Student deleted successfully"
    }
    ```