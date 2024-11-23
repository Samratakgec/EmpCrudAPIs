# Employee Management API

## Description
This is a RESTful API developed using **Spring Boot** for managing employee records. The API allows the creation, retrieval, updating, and deletion (CRUD operations) of employee records, which are stored in a **MySQL** database.

## Features
- **CRUD Operations**: Create, Read, Update, and Delete employee records.
- **Validation**: Input validation for employee details (e.g., salary must be positive).
- **Error Handling**: Standardized error responses using custom exception handling.

## Technologies Used
- **Spring Boot**: To implement RESTful endpoints.
- **MySQL**: Database for storing employee details.
- **Hibernate**: ORM framework for database interaction.
- **Validation**: JSR-303/JSR-380 annotations for input validation.

## API Endpoints

### 1. Create Employee
- **Endpoint**: `POST /api/employees`
- **Description**: Creates a new employee record.
- **Request Body**:
    ```json
    {
        "name": "John Doe",
        "phone": "123-456-7890",
        "email": "john.doe@example.com",
        "salary": 5000.0
    }
    ```
- **Validation**:
    - `name`, `phone`, and `email` cannot be empty.
    - `salary` must be a positive number.
- **Response**:
    - **HTTP 201 Created**: If the employee is successfully created.
    - **HTTP 400 Bad Request**: If any validation fails (e.g., empty fields or negative salary).
    - **Response Example**:
        ```json
        {
            "message": "Employee created successfully!"
        }
        ```

### 2. Get All Employees
- **Endpoint**: `GET /api/employees`
- **Description**: Retrieves a list of all employees.
- **Response**:
    - **HTTP 200 OK**: If employees are found.
    - **Response Example**:
        ```json
        [
            {
                "id": 1,
                "name": "John Doe",
                "phone": "123-456-7890",
                "email": "john.doe@example.com",
                "salary": 5000.0
            },
            {
                "id": 2,
                "name": "Jane Smith",
                "phone": "098-765-4321",
                "email": "jane.smith@example.com",
                "salary": 6000.0
            }
        ]
        ```

### 3. Get Employee by ID
- **Endpoint**: `GET /api/employees/{id}`
- **Description**: Retrieves a specific employee by ID.
- **Response**:
    - **HTTP 200 OK**: If employee is found.
    - **HTTP 404 Not Found**: If employee with the given ID does not exist.
    - **Response Example**:
        ```json
        {
            "id": 1,
            "name": "John Doe",
            "phone": "123-456-7890",
            "email": "john.doe@example.com",
            "salary": 5000.0
        }
        ```

### 4. Update Employee
- **Endpoint**: `PUT /api/employees/{id}`
- **Description**: Updates the employee details for the given ID.
- **Request Body**:
    ```json
    {
        "name": "John Updated",
        "phone": "123-456-7899",
        "email": "john.updated@example.com",
        "salary": 5500.0
    }
    ```
- **Response**:
    - **HTTP 200 OK**: If the employee is successfully updated.
    - **HTTP 400 Bad Request**: If the salary is negative or validation fails.
    - **HTTP 404 Not Found**: If employee with the given ID is not found.
    - **Response Example**:
        ```json
        {
            "message": "Employee updated successfully!"
        }
        ```

### 5. Delete Employee
- **Endpoint**: `DELETE /api/employees/{id}`
- **Description**: Deletes an employee by ID.
- **Response**:
    - **HTTP 204 No Content**: If the employee is successfully deleted.
    - **HTTP 404 Not Found**: If employee with the given ID is not found.
    - **Response Example**:
        ```json
        {
            "message": "Employee deleted successfully!"
        }
        ```

## Error Handling

### Custom Error Response
The API returns detailed error messages in case of any issues such as validation errors or if an employee is not found.

- **Validation Errors**: When invalid input is provided (e.g., negative salary or empty fields), the response will return with HTTP 400 status and an error message.
    - Example:
        ```json
        {
            "message": "Salary cannot be negative!"
        }
        ```
  
- **Employee Not Found**: When trying to update or delete a non-existing employee, the response will return HTTP 404 status.
    - Example:
        ```json
        {
            "message": "Employee with ID 1 not found"
        }
        ```

## Data Model
### Employee
- **id**: The unique identifier for the employee (auto-generated).
- **name**: The name of the employee.
- **phone**: The phone number of the employee.
- **email**: The email address of the employee.
- **salary**: The salary of the employee (must be positive).

### Employee Entity (Database)
This entity maps to a table `Empdata` in the database.

- **id** (Primary Key): Auto-incremented ID.
- **name**: Employee's name.
- **phone**: Employee's phone number.
- **email**: Employee's email address.
- **salary**: Employee's salary.

## Database Configuration
The application uses a MySQL database to persist employee records. You can configure the database settings in the `application.properties` file as shown below:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/empdb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
