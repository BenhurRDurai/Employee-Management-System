# Employee Management System

## Overview
This is a Spring Boot application that allows importing employee details from an Excel sheet and storing them in a database. It also provides REST API endpoints to retrieve, update, and delete employee records.

## Features
- Upload an Excel file containing employee details.
- Parse and store employee data dynamically based on Excel headers.
- Retrieve all employees or a specific employee.
- Update employee details.
- Delete an employee record.

## Technologies Used
- Java 17
- Spring Boot
- Spring Data JPA
- Apache POI (for Excel processing)
- H2/MySQL Database

## API Endpoints
### 1. Upload Employee Excel File
**Endpoint:** `POST /employees/upload`
**Description:** Uploads an Excel file and saves employee details.
**Request:**
- `file`: Multipart file (Excel `.xlsx` format required)

**Response:**
- `200 OK` if uploaded successfully
- `400 Bad Request` for invalid files

### 2. Add Employee
**Endpoint:** `POST /employees/addEmployee`
**Description:** Add employee's details.
**Request Body (JSON):**
```json
{
  "name": "Name",
  "email": "email@example.com",
  "department": "Department"
}
```

### 3. Get All Employees
**Endpoint:** `GET /employees`
**Description:** Retrieves all employees from the database.

### 4. Get Employee by ID
**Endpoint:** `GET /employees/{id}`
**Description:** Fetches a specific employee by ID.

### 5. Update Employee
**Endpoint:** `PUT /employees/{id}`
**Description:** Updates an employee's details.
**Request Body (JSON):**
```json
{
  "name": "Updated Name",
  "email": "updated.email@example.com",
  "department": "Updated Department"
}
```

### 6. Delete Employee
**Endpoint:** `DELETE /employees/{id}`
**Description:** Deletes an employee by ID.

## How to Run
1. Clone the repository.
2. Install dependencies:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
4. Use Postman or a similar tool to test the endpoints.

## Database Configuration
By default, the application uses an **H2 in-memory database**. To use MySQL, update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employees_db
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

## Sample Excel File Format
| name   | email               | department |
|--------|---------------------|-----------|
| John Doe | john@example.com | IT        |
| Jane Smith | jane@example.com | HR        |

Make sure the **column headers match** the database fields.

## Dependencies
Ensure you have the following dependency in `pom.xml` for Apache POI:
```xml
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.3</version>
</dependency>
```

## Author
[Benhur R Durai]

## License
Benhur


