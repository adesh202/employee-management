package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @Test
    void testAddEmployee() {
        EmployeeService service = new EmployeeService();
        Employee employee = new Employee(1L, "John Doe", "Developer");
        service.addEmployee(employee);

        assertEquals(1, service.getAllEmployees().size());
        assertEquals("John Doe", service.getEmployeeById(1L).getName());
    }
}
