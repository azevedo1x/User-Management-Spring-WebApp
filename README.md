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
- **Database Integration** using **HSQLDB**.
- **Thymeleaf Templates** for dynamic front-end rendering.
- **Bootstrap** for responsive design.

---

## **Technologies Used**
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **HSQLDB**
- **Thymeleaf**
- **Bootstrap 5**

---

## **Setup Instructions**

### 1. **Clone the Repository**
```bash
git clone https://github.com/your-repo/user-management-spring-boot.git
cd user-management-spring-boot
```

### 2. **Start HSQLDB Server**
Run the following command to start the database:
```bash
java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 banco
```

### 3. **Configure Database**
Check `Banco.java` for the current database configuration:
```java
dataSource.setUrl("jdbc:hsqldb:hsql://localhost/banco");
dataSource.setUsername("SA");
dataSource.setPassword("");
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

------------------------------------------

# **Aplicação Web de Gerenciamento de Usuários (Spring Boot)**

Este projeto é uma aplicação web **Spring Boot** projetada para gerenciar registros de usuários. Ele demonstra operações CRUD (Criar, Ler, Atualizar, Deletar) usando **Spring MVC**, **Spring Data JPA** e **Thymeleaf** para renderização no front-end.

---

## **Funcionalidades**
- **Gerenciamento de Usuários:**
  - Cadastro de novos usuários.
  - Edição de detalhes de usuários existentes.
  - Listagem de todos os usuários registrados.
  - Remoção de usuários pelo login.
  
- **Validação de Formulários** com exceções personalizadas.
- **Integração com Banco de Dados** usando **HSQLDB**.
- **Templates Thymeleaf** para renderização dinâmica do front-end.
- **Bootstrap** para design responsivo.

---

## **Tecnologias Utilizadas**
- **Java 17+**
- **Spring Boot 3+**
- **Spring Data JPA**
- **HSQLDB**
- **Thymeleaf**
- **Bootstrap 5**

---

## **Instruções de Configuração**

### 1. **Clonar o Repositório**
```bash
git clone https://github.com/seu-repo/user-management-spring-boot.git
cd user-management-spring-boot
```

### 2. **Iniciar o Servidor HSQLDB**
Execute o seguinte comando para iniciar o banco de dados:
```bash
java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:mydb --dbname.0 banco
```

### 3. **Configurar o Banco de Dados**
Verifique `Banco.java` para a configuração atual do banco de dados:
```java
dataSource.setUrl("jdbc:hsqldb:hsql://localhost/banco");
dataSource.setUsername("SA");
dataSource.setPassword("");
```
Certifique-se de que o servidor de banco de dados está sendo executado no `localhost`.

### 4. **Executar a Aplicação**
```bash
./mvnw spring-boot:run
```
Acesse o app em [http://localhost:8080](http://localhost:8080).

---

## **EndPoints**

- **Home:** `/`
- **Cadastrar Usuário:** `/cadastrarUsuario`
- **Editar Usuário:** `/editarUsuario`
- **Remover Usuário:** `/removerUsuario`
- **Listar Usuários:** `/listarUsuario`

---

## **Estrutura do Projeto**

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

## **Exemplos de Páginas Thymeleaf**
- **`formUsuario.html`:** Formulário para criar/editar usuários.
- **`listarUsuario.html`:** Exibição dos usuários registrados.
- **`formRemocao.html`:** Formulário para remover usuários.
- **`index.html`:** Página inicial com links de navegação.

---

## **Contribuindo**
Sinta-se à vontade para fazer um fork deste projeto e enviar pull requests. Para mudanças maiores, abra uma issue primeiro para discutir o que você gostaria de melhorar.

---

## **Contato**
Para dúvidas ou feedback, entre em contato pelo e-mail: **gabriel.azeve04@gmail.com**.
