package com.opencsvdemo.service;

import com.opencsvdemo.model.Employee;
import com.opencsvdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String processCsvData(List<Employee> empList) throws Exception {
        employeeRepository.saveAll(empList);
        //employeeRepository.save(empList);
        return "Saved CSV data successfully";
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        Employee employee1 = employeeRepository.findById(employee.getEmpId()).orElseThrow(() -> new RuntimeException("Not found"));
        employeeRepository.updateEmployee(employee.getEmpId(),employee.getEmpName(),
                employee.getEmpMailId(),employee.getEmpSkillSet());
        return employee;
    }
}
