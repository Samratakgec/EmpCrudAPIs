package com.Samrat.EmpCrud.Service;

import com.Samrat.EmpCrud.Model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface EmpInterface {
   String createEmployee(Employee employee) ;
   List<Employee> readEmployees() ;

   String updateEmployee(Employee employee,Long id) ;

   Boolean deleteEmployee(Long id) ;
}
