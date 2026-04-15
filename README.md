#  WinWin Test Task – Backend Engineer

This project implements a simple microservice-based architecture using **Spring Boot**, **PostgreSQL**, and **Docker**.

##  Architecture

The system consists of:

* **auth-api** (Service A)

    * Handles authentication (register/login)
    * Secured endpoint `/process`
    * Calls data-api
    * Stores processing logs in PostgreSQL

* **data-api** (Service B)

    * Internal service
    * Performs text transformation
    * Accessible only with internal token

* **PostgreSQL**

    * Stores users and processing logs

---

##  Technologies

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* Docker & Docker Compose
* JWT (JSON Web Token)
* BCrypt password hashing

---

##  Authentication Flow

1. User registers via `/api/auth/register`
2. User logs in via `/api/auth/login`
3. Server returns JWT token
4. Token is used for accessing protected endpoints

---

##  API Endpoints

###  Auth (auth-api)

#### Register

```http
POST /api/auth/register
```

Body:

```json
{
  "email": "test@test.com",
  "password": "1234"
}
```

---

#### Login

```http
POST /api/auth/login
```

Response:

```json
{
  "token": "JWT_TOKEN"
}
```

---

###  Process (auth-api)

```http
POST /api/process
Authorization: Bearer <TOKEN>
```

Body:

```json
{
  "text": "hello world"
}
```

Response:

```json
{
  "result": "HELLO WORLD"
}
```

---

###  Transform (data-api)

```http
POST /api/transform
Header: X-Internal-Token=<INTERNAL_TOKEN>
```

Body:

```json
{
  "text": "hello world"
}
```

Response:

```json
{
  "result": "HELLO WORLD"
}
```

---

##  Database Schema

### users

* id (UUID)
* email (unique)
* password_hash

### processing_log

* id (UUID)
* user_id
* input_text
* output_text
* created_at

---

##  Running with Docker

### 1. Build services

```bash
mvn -f auth-api/pom.xml clean package -DskipTests
mvn -f data-api/pom.xml clean package -DskipTests
```

---

### 2. Run containers

```bash
docker compose up -d --build
```

---

##  Services

| Service  | URL                   |
| auth-api | http://localhost:8080 |
| data-api | http://localhost:8081 |
| postgres | localhost:5432        |

---

##  Environment Variables

* `POSTGRES_URL`
* `POSTGRES_USER`
* `POSTGRES_PASSWORD`
* `JWT_SECRET`
* `INTERNAL_TOKEN`

---

##  Security

* Passwords are hashed using BCrypt
* JWT is used for authentication
* data-api is protected via internal header (`X-Internal-Token`)
* Sensitive data is not logged

---

##  Notes

* Simple implementation focused on core functionality
* No overengineering
* Clear separation between services

---

##  How to Test

### 1. Register

```bash
curl -X POST http://localhost:8080/api/auth/register
```

### 2. Login

```bash
curl -X POST http://localhost:8080/api/auth/login
```

### 3. Process

```bash
curl -X POST http://localhost:8080/api/process \
  -H "Authorization: Bearer <TOKEN>"
```

---

## 💡 Author

Backend test implementation for WinWin
