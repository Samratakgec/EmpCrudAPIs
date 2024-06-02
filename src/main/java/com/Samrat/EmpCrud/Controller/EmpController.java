package com.Samrat.EmpCrud.Controller;

import com.Samrat.EmpCrud.Model.Employee;
import com.Samrat.EmpCrud.Service.EmpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    EmpServiceImpl empService ;
    @PostMapping("emp")
    String create(@RequestBody Employee employee)
    {
        empService.createEmployee(employee) ;
        return "created successfully" ;
    }

    @GetMapping("emp")
    List<Employee> read()
    {
        return empService.readEmployees();
    }

    @PutMapping("emp/{id}")
    String update(@RequestBody Employee employee,@PathVariable Long id)
    {

        return empService.updateEmployee(employee,id)  ;
    }

    @DeleteMapping("emp/{id}")
    String delete(@PathVariable Long id)
    {
        if(empService.deleteEmployee(id) )
        return "deleted successfully" ;
        else return  "Employee not found" ;
    }

}
