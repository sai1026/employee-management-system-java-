package com.example.Employee.model.test;
import com.example.Employee.model.Employee; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testEmployeeConstructorAndGetters() {
        // Arrange
        Employee employee = new Employee("John Doe", "IT", 50000);

        // Act & Assert
        assertEquals("John Doe", employee.getName());
        assertEquals("IT", employee.getDepartment());
        assertEquals(50000, employee.getSalary());
    }

    @Test
    void testSetters() {
        // Arrange
        Employee employee = new Employee();
        
        // Act
        employee.setId(1L);
        employee.setName("Alice");
        employee.setDepartment("HR");
        employee.setSalary(60000);

        // Assert
        assertEquals(1L, employee.getId());
        assertEquals("Alice", employee.getName());
        assertEquals("HR", employee.getDepartment());
        assertEquals(60000, employee.getSalary());
    }
}
