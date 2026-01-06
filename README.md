# User Management System

A simple Spring Boot web application for managing users with CRUD operations, form validation, and a clean, responsive UI.

## Features

- Create new users with validation
- Update existing user information
- Delete users from the system
- List all registered users
- Form validation with error handling
- Responsive design with modern UI
- User-friendly flash messages

## Technologies Used

- **Backend**
  - Spring Boot
  - Spring MVC
  - Spring Data JPA
  - PostgreSQL
  - Hibernate Validator
  - Thymeleaf

- **Frontend**
  - Bootstrap 5.3.1
  - Font Awesome 6.4.0
  - Custom responsive styling

## Prerequisites

- JDK 17 or later
- Maven
- PostgreSQL
- Git

## Database Configuration

The application is configured to use PostgreSQL with the following default settings:

```properties
spring.datasource.url=jdbc:postgresql://localhost/userManagement
spring.datasource.username=postgres
spring.datasource.password=postgres
```

You can modify these settings in `application.properties` to match your database configuration.

## Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/azevedo1x/User-Management-Spring-WebApp
   ```

2. Create PostgreSQL database:
   ```sql
   CREATE DATABASE userManagement;
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

5. Access the application at `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/programacao/web/
│   │   ├── controller/
│   │   │   └── UserController.java
│   │   ├── dto/
│   │   │   └── UserDTO.java
│   │   ├── exception/
│   │   │   └── UserException.java
│   │   ├── model/
│   │   │   └── User.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   └── service/
│   │       └── UserService.java
│   │
│   └── resources/
│       ├── static/
│       │   └── css/
│       │       └── styles.css
│       ├── templates/
│       │   ├── index.html        # Main landing page
│       │   ├── recordView.html   # Create/Update user form
│       │   ├── deleteView.html   # Delete user form
│       │   └── readView.html     # List users view
│       └── application.properties
```

## User Validation Rules

- Login is required and must be unique
- Name is required
- Email is required and must be valid
- Password must be between 4 and 8 characters
- Password cannot be the same as login
- Email and password confirmation must match

## Features in Detail

### User Creation
- Form-based user registration
- Real-time validation
- Duplicate login prevention
- Email confirmation check
- Password validation

### User Update
- Existing user data modification
- Validation of updated information
- Error handling for non-existent users

### User Deletion
- Login-based user removal
- Confirmation messages
- Error handling for non-existent users

### User Listing
- Display of all registered users
- Clean and organized user interface
- Responsive design for all screen sizes

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/yourFeature`)
3. Commit your changes (`git commit -m 'Add some yourFeature'`)
4. Push to the branch (`git push origin feature/yourFeature`)
5. Open a Pull Request

## Examples for you to contribute

- Password encryption
- User session management
- CSRF protection
- Rate limiting
- Input sanitization
