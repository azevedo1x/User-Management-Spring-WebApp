# User Management System

A secure Spring Boot web application for managing users with CRUD operations, BCrypt password encryption, form validation, and a clean, responsive UI.

## Features

- ✅ Create new users with strong password validation
- ✅ Update existing user information
- ✅ Delete users from the system
- ✅ List all registered users
- ✅ BCrypt password encryption
- ✅ Comprehensive form validation with error handling
- ✅ Responsive design with Bootstrap
- ✅ User-friendly flash messages
- ✅ Global exception handling
- ✅ Comprehensive test coverage

## Technologies Used

- **Backend**
  - Spring Boot 3.4.2
  - Spring MVC
  - Spring Security (BCrypt password encoding)
  - Spring Data JPA
  - PostgreSQL
  - Bean Validation (Jakarta Validation)
  - Thymeleaf
  - SLF4J/Logback

- **Frontend**
  - Bootstrap 5.3.1
  - Font Awesome 6.4.0
  - Thymeleaf templates

- **Testing**
  - JUnit 5
  - Mockito
  - Spring Boot Test
  - H2 in-memory database

## Prerequisites

- JDK 20 or later
- Maven 3.8+
- PostgreSQL 15+
- Git

## Database Configuration

### Step 1: Create the Database

```bash
# Using psql or pgAdmin
createdb userManagement

# Or using PostgreSQL command line
psql -U postgres
CREATE DATABASE userManagement;
```

### Step 2: Set Environment Variables

The application uses environment variables for database configuration to avoid hardcoding credentials.

**On Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/userManagement"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your_secure_password"
```

**On Linux/Mac:**
```bash
export DB_URL="jdbc:postgresql://localhost:5432/userManagement"
export DB_USERNAME="postgres"
export DB_PASSWORD="your_secure_password"
```

**Alternative: Create application-dev.properties** (gitignored)

Create `src/main/resources/application-dev.properties`:
```properties
spring.profiles.active=dev
DB_URL=jdbc:postgresql://localhost:5432/userManagement
DB_USERNAME=postgres
DB_PASSWORD=your_secure_password
JPA_SHOW_SQL=true
JPA_DDL_AUTO=update
```

Then run with: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev`

## Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/azevedo1x/User-Management-Spring-WebApp
   cd User-Management-Spring-WebApp
   ```

2. Create PostgreSQL database:
   ```sql
   CREATE DATABASE userManagement;
   ```

3. Set environment variables (see Database Configuration section above)

4. Build the project:
   ```bash
   ./mvnw clean install
   ```

5. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

6. Access the application at `http://localhost:8080`

## Running Tests

```bash
# Run all tests
./mvnw test

# Run with coverage report
./mvnw test jacoco:report

# Run specific test class
./mvnw test -Dtest=UserServiceTest
```

## Project Structure

```
src/
├── main/
│   ├── java/programacao/web/
│   │   ├── config/
│   │   │   └── SecurityConfig.java        # Security & password encoding
│   │   ├── controller/
│   │   │   └── UserController.java        # MVC controllers
│   │   ├── dto/
│   │   │   └── UserDTO.java               # Data transfer objects
│   │   ├── exception/
│   │   │   ├── ApplicationException.java
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   ├── UserAlreadyExistsException.java
│   │   │   ├── UserNotFoundException.java
│   │   │   └── ValidationException.java
│   │   ├── model/
│   │   │   └── User.java                  # JPA entity
│   │   ├── repository/
│   │   │   └── UserRepository.java        # Data access layer
│   │   └── service/
│   │       └── UserService.java           # Business logic
│   │
│   └── resources/
│       ├── templates/
│       │   ├── index.html                 # Main landing page
│       │   ├── recordView.html            # Create/Update user form
│       │   ├── deleteView.html            # Delete user form
│       │   └── readView.html              # List users view
│       └── application.properties
│
└── test/
    ├── java/programacao/web/
    │   ├── controller/
    │   │   └── UserControllerIntegrationTest.java
    │   ├── repository/
    │   │   └── UserRepositoryTest.java
    │   └── service/
    │       └── UserServiceTest.java
    └── resources/
        └── application-test.properties

```

## User Validation Rules

- **Login**: Required, 3-50 characters, must be unique
- **Name**: Required, 2-100 characters
- **Email**: Required, must be valid email format, must match confirmation
- **Password**: Required, minimum 8 characters, must contain:
  - At least one uppercase letter
  - At least one lowercase letter
  - At least one digit
  - At least one special character (@#$%^&+=!*)
  - Password cannot be the same as login
  - Must match password confirmation

## Security Features

- ✅ **BCrypt Password Hashing**: All passwords are encrypted using BCrypt with salt
- ✅ **Environment Variables**: Database credentials stored in environment variables
- ✅ **Input Validation**: Comprehensive server-side validation
- ✅ **SQL Injection Protection**: JPA/Hibernate parameterized queries
- ✅ **Exception Handling**: Global exception handler with logging

## API Endpoints

| Method | Endpoint      | Description              |
|--------|---------------|--------------------------|
| GET    | /             | Home page                |
| GET    | /register     | Show registration form   |
| POST   | /register     | Create new user          |
| GET    | /update       | Show update form         |
| POST   | /update       | Update existing user     |
| GET    | /delete       | Show delete form         |
| POST   | /delete       | Delete user              |
| GET    | /read         | List all users           |
