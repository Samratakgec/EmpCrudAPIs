package com.Samrat.EmpCrud.Exception;

import javax.persistence.EntityNotFoundException;

public class EmployeeNotFoundException extends EntityNotFoundException {

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
