package com.ben.code.EmployeeDetailsExcelFileUpload.controller;


import com.ben.code.EmployeeDetailsExcelFileUpload.entity.Employee;
import com.ben.code.EmployeeDetailsExcelFileUpload.service.EmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam("file")MultipartFile file){

        if(file.isEmpty()){
            return "Please upload an excel file";
        }
        try{
            employeeService.saveEmployeesFromExcel(file.getInputStream());
            return "File uploadedd successfully and data saved";
        } catch (IOException e) {
            return "Error processing file: " + e.getMessage();
        }
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }


    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        return employeeService.updateEmployee(id,employeeDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }


}
