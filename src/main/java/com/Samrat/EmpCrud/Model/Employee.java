package com.Samrat.EmpCrud.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Employee {

    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Phone number cannot be empty")
    private String phone;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Salary cannot be null")
    @Min(value = 0, message = "Salary must be a positive value")
    private Double salary;  // Ensures salary can't be negative and not null
}