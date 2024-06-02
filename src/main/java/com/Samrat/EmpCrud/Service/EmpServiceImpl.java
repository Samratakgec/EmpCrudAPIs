package com.Samrat.EmpCrud.Service;

import com.Samrat.EmpCrud.Model.Employee;
import com.Samrat.EmpCrud.Repository.EmployeeEntity;
import com.Samrat.EmpCrud.Repository.EmployeeRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmpServiceImpl implements EmpInterface{


    @Autowired
   private EmployeeRepository employeeRepository ;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);
        employeeRepository.save(employeeEntity) ;
        return "Created Successfully";
    }

    @Override
    public List<Employee> readEmployees() {

        List<Employee> employeeList = new ArrayList<>() ;
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll() ;
       for (EmployeeEntity employeeEntity: employeeEntityList)
       {
           Employee employee = new Employee() ;
           BeanUtils.copyProperties(employeeEntity,employee);
           employeeList.add(employee) ;
       }
        return employeeList;
    }

    @Override
    public String updateEmployee(Employee employee,Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get() ;
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setName(employee.getName());
        employeeEntity.setPhone(employee.getPhone());
        employeeRepository.save(employeeEntity) ;
        return "updated successfully";
    }

    @Override
    public Boolean deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get() ;
        employeeRepository.delete(employeeEntity);
        return true;
    }
}
