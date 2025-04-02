package com.ben.code.EmployeeDetailsExcelFileUpload.service;

import com.ben.code.EmployeeDetailsExcelFileUpload.entity.Employee;
import com.ben.code.EmployeeDetailsExcelFileUpload.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveEmployeesFromExcel(InputStream excelFile) throws IOException {
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        Map<String, Integer> columnIndexMap = new HashMap<>();

        for (Cell cell : headerRow){
            columnIndexMap.put(cell.getStringCellValue().toLowerCase(), cell.getColumnIndex());
        }

        for (int i = 1; i <= sheet.getLastRowNum(); i++){
            Row row = sheet.getRow(i);
            if (row == null) continue;

            Employee employee = new Employee();
            employee.setName(row.getCell(columnIndexMap.get("name")).getStringCellValue());
            employee.setEmail(row.getCell(columnIndexMap.get("email")).getStringCellValue());
            employee.setDepartment(row.getCell(columnIndexMap.get("department")).getStringCellValue());

            employeeRepository.save(employee);

        }

        workbook.close();

    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setDepartment(employeeDetails.getDepartment());
            return employeeRepository.save(employee);
        }).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
