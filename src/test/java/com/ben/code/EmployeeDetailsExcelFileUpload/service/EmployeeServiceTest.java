package com.ben.code.EmployeeDetailsExcelFileUpload.service;

import com.ben.code.EmployeeDetailsExcelFileUpload.entity.Employee;
import com.ben.code.EmployeeDetailsExcelFileUpload.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    public void shouldSaveEmployee(){
        Employee employee = new Employee(0, "Ben", "brd@gmail.com", "Delivery");

        when (employeeRepository.save(employee)).thenReturn(new Employee(1, "Ben", "brd@gmail.com", "Delivery"));

        Employee result = employeeService.addEmployee(employee);
        assertEquals("Ben", result.getName());
        assertEquals("brd@gmail.com",result.getEmail());
        assertEquals("Delivery",result.getDepartment());

        verify(employeeRepository, times(1)).save(employee);

    }

    @Test
    public void shouldReturnAllEmployees(){
        List<Employee> employees = new ArrayList<>();
        Employee E1 = new Employee(1,"Ben","brdurai@gmail.com","Delivery");
        Employee E2 = new Employee(2,"Priya","priyaben@gmail.com","Manager");
        employees.add(E1);
        employees.add(E2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> responseList = employeeService.getAllEmployees();

        assertEquals(2,responseList.size());

        assertEquals("Ben",responseList.get(0).getName());

        assertEquals("Priya", responseList.get(1).getName());

        assertEquals("Manager",responseList.get(1).getDepartment());

        assertEquals(2,responseList.get(1).getId());

        verify(employeeRepository, times(1)).findAll();

    }

    @Test
    public void shouldReturnById(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,"Ben","brd@gmail.com","COO"));
        employees.add(new Employee(2,"Priya","priya@gmail.com","Accounts"));


        when(employeeRepository.findById(2L)).thenReturn(Optional.ofNullable(employees.get(1)));

        Optional<Employee> res = employeeService.getEmployeeById(2L);

        assertEquals("Priya", res.get().getName());
        assertEquals("Accounts", res.get().getDepartment());

        verify(employeeRepository,times(1)).findById(2L);
    }

}
