package com.opencsvdemo.controller;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsvdemo.model.Employee;
import com.opencsvdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/csv_file")
    public String processCsvFile(@RequestParam("file") MultipartFile file) throws Exception {
        //Employee employee = new Employee();
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            System.out.print(reader.read());
            // create csv bean reader
            //CsvToBean<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
            List<Employee> csvToBean = new CsvToBeanBuilder<Employee>(reader)
                    .withType(Employee.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreEmptyLine(true)
                    .build()
                    .parse();

            // convert `CsvToBean` object to list of employees
            //List<Employee> empList = csvToBean.parse();
            csvToBean.forEach(System.out::println);
            return employeeService.processCsvData(csvToBean);
        } catch (IOException ex) {ex.getStackTrace();}
        return "";
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.FOUND);
    }

    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }
}
