# **User Management Web Application (Spring Boot)**

This project is a **Spring Boot** web application designed to manage user registrations. It demonstrates CRUD operations (Create, Read, Update, Delete) using **Spring MVC**, **Spring Data JPA**, and **Thymeleaf** for frontend rendering.

---

## **Features**
- **User Management:**
  - Register new users.
  - Edit existing user details.
  - List all registered users.
  - Remove users by login.
  
- **Form Validation** with custom exceptions.
- **Database Integration** using **POSTGRESQL**.
- **Thymeleaf Templates** for dynamic front-end rendering.
- **Bootstrap** for responsive design.

---

## **Technologies Used**
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **POSTGRESQL**
- **Thymeleaf**
- **Bootstrap 5**

---

## **Setup Instructions**

### 1. **Clone the Repository**
```bash
git clone https://github.com/azevedo1x/user-management-spring-webapp.git
cd user-management-spring-webapp
```

<!-- skip this step 2. **Start HSQLDB Server**
Run the following command to start the database:
```bash
java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 banco
``` -->

### 2. **Create PostegreSQL database**
CREATE DATABASE gerenciamentoDeUsuarios;

### 3. **Configure Database**
Check `Banco.java` for the current database configuration:
```java
dataSource.setUrl("jdbc:postgresql://localhost/gerenciamentoDeUsuarios");
dataSource.setUsername("your_username");
dataSource.setPassword("your_password");
```
Ensure the database server is running on `localhost`.

### 4. **Run the Application**
```bash
./mvnw spring-boot:run
```
Access the app at [http://localhost:8080](http://localhost:8080).

---

## **EndPoints**

- **Home:** `/`
- **Register User:** `/cadastrarUsuario`
- **Edit User:** `/editarUsuario`
- **Remove User:** `/removerUsuario`
- **List Users:** `/listarUsuario`

---

## **Project Structure**

```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── programacao.web
│   │   │       ├── controller
│   │   │       ├── data
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       └── WebApplication.java
│   │   └── resources
│   │       └── templates
│   └── test
└── pom.xml
```

---

## **Sample Thymeleaf Pages**
- **`formUsuario.html`:** Form for creating/editing users.
- **`listarUsuario.html`:** Display registered users.
- **`formRemocao.html`:** Form for removing users.
- **`index.html`:** Homepage with navigation links.

---

## **Contributing**
Feel free to fork this project and submit pull requests. For major changes, open an issue first to discuss what you would like to improve.

--- 

## **Contact**
For questions or feedback, reach out at: **gabriel.azeve04@gmail.com**.
