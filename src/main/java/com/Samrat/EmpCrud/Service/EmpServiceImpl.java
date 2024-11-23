package com.Samrat.EmpCrud.Service;

import com.Samrat.EmpCrud.Exception.EmployeeNotFoundException;
import com.Samrat.EmpCrud.Model.Employee;
import com.Samrat.EmpCrud.Repository.EmployeeEntity;
import com.Samrat.EmpCrud.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpServiceImpl implements EmpInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String createEmployee(Employee employee) {
        if (employee.getSalary() < 0) {
            return "Salary cannot be negative!";
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return "Employee created successfully!";
    }

    @Override
    public List<Employee> readEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        for (EmployeeEntity entity : employeeEntityList) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(entity, employee);
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public String updateEmployee(Employee employee, Long id) {
        if (employee.getSalary() < 0) {
            return "Salary cannot be negative!";
        }

        Optional<EmployeeEntity> employeeEntityOpt = employeeRepository.findById(id);
        if (employeeEntityOpt.isPresent()) {
            EmployeeEntity employeeEntity = employeeEntityOpt.get();
            employeeEntity.setName(employee.getName());
            employeeEntity.setPhone(employee.getPhone());
            employeeEntity.setEmail(employee.getEmail());
            employeeEntity.setSalary(employee.getSalary());
            employeeRepository.save(employeeEntity);
            return "Employee updated successfully!";
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        Optional<EmployeeEntity> employeeEntityOpt = employeeRepository.findById(id);
        if (employeeEntityOpt.isPresent()) {
            employeeRepository.delete(employeeEntityOpt.get());
            return true;
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found");
        }
    }
}
