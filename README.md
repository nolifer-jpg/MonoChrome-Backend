# MonoChrome – Habit Tracking Backend

MonoChrome is a minimalist **task / habit tracking backend** built using **Spring Boot**.  
It exposes clean REST APIs designed to be consumed by a frontend (React, Next.js, etc.).

This project focuses on **clean backend architecture**, proper layering, validation, and correct HTTP semantics.

---

## Tech Stack

- Java 21
- Spring Boot
- Spring Web (REST)
- Spring Data JPA
- H2 (in-memory database for development)
- Maven

---

## Architecture

The project follows a clean layered structure:

```
src/main/java/com/monochrome
├── controller   # REST API layer (thin controllers)
├── service      # Business logic
├── repository   # Data access (JPA)
├── domain       # JPA entities
├── dto          # Request / Response DTOs
├── exception    # Global exception handling
```

**Controllers** handle HTTP concerns only  
**Services** contain business logic  
**Repositories** interact with the database  
**DTOs** prevent entity leakage outside the service layer

---

## Running the Project

### Prerequisites
- Java 21+
- Maven

### Run locally
```bash
./mvnw spring-boot:run
```

The server will start at:
```
http://localhost:8080
```

> Note: H2 is used for development, so data resets on restart.

---

## API Endpoints

### Create Task
```http
POST /tasks
Content-Type: application/json
```

Request body:
```json
{
  "description": "Read for 30 minutes"
}
```

Response `201 Created`:
```json
{
  "id": 1,
  "description": "Read for 30 minutes",
  "completed": false,
  "createdAt": "2026-02-08T08:43:16.545Z"
}
```

---

### Get All Tasks
```http
GET /tasks
```

Response `200 OK`:
```json
[
  {
    "id": 1,
    "description": "Read for 30 minutes",
    "completed": false,
    "createdAt": "2026-02-08T08:43:16.545Z"
  }
]
```

---

### Mark Task as Completed
```http
PATCH /tasks/{id}/complete
```

Response:
```
204 No Content
```

---

### Delete Task
```http
DELETE /tasks/{id}
```

Response:
```
204 No Content
```

---

## Error Handling

### Validation Error
```json
{
  "error": "VALIDATION_ERROR",
  "details": {
    "description": "must not be blank"
  }
}
```

### Task Not Found
```json
{
  "error": "NOT_FOUND",
  "message": "Task with id 5 not found"
}
```

---

## Notes for Frontend Developers

- JSON-only API
- No authentication yet
- No pagination yet
- Predictable response structure
- Designed for easy frontend integration

---

## Future Improvements

- Pagination for task listing
- Authentication (JWT)
- MySQL / PostgreSQL support
- OpenAPI / Swagger documentation
- CORS configuration for production

---

## Author

Built as a university backend project to learn **Spring Boot**, clean architecture, and RESTful API design.
