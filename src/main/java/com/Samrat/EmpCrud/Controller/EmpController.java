package com.Samrat.EmpCrud.Controller;

import com.Samrat.EmpCrud.Model.Employee;
import com.Samrat.EmpCrud.Service.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
public class EmpController {

    @Autowired
    private EmpServiceImpl empService;

    // Helper method to handle validation errors
    private ResponseEntity<Object> handleValidationErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST); // HTTP 400 Bad Request
        }
        return null; // No errors
    }

    // Create Employee
    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody Employee employee, BindingResult bindingResult) {
        ResponseEntity<Object> validationResponse = handleValidationErrors(bindingResult);
        if (validationResponse != null) {
            return validationResponse;  // Return validation errors if present
        }

        String response = empService.createEmployee(employee);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // HTTP 201 Created
    }

    // Read all Employees
    @GetMapping
    public ResponseEntity<List<Employee>> read() {
        List<Employee> employees = empService.readEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK); // HTTP 200 OK
    }

    // Update Employee
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody Employee employee, @PathVariable Long id, BindingResult bindingResult) {
        ResponseEntity<Object> validationResponse = handleValidationErrors(bindingResult);
        if (validationResponse != null) {
            return validationResponse; // Return validation errors if present
        }

        String response = empService.updateEmployee(employee, id);
        if (response.equals("Employee not found!")) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // HTTP 404 Not Found
        }
        return new ResponseEntity<>(response, HttpStatus.OK); // HTTP 200 OK
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        boolean deleted = empService.deleteEmployee(id);
        if (deleted) {
            return new ResponseEntity<>("Deleted successfully", HttpStatus.NO_CONTENT); // HTTP 204 No Content
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND); // HTTP 404 Not Found
    }
}
