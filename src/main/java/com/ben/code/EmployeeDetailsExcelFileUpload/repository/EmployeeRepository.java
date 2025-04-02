package com.ben.code.EmployeeDetailsExcelFileUpload.repository;

import com.ben.code.EmployeeDetailsExcelFileUpload.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
