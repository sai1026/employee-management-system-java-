package com.example.Employee.service.test;


import com.example.Employee.model.Employee;

import com.example.Employee.repository.EmployeeRepository;
import com.example.Employee.service.EmployeeServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

import java.util.Optional;
@SpringBootTest
class EmployeeServicesTest {
	@Autowired
    private EmployeeServices employeeServices;  
@MockBean
    private EmployeeRepository employeeRepository;  // Mock repository dependency

    private Employee employee;
    private Employee updatedEmployee;

    @BeforeEach
    void setUp() {
        // Initialize mock objects and set up data before each test
        employee = new Employee("John Doe", "IT", 50000);
        updatedEmployee = new Employee("John Doe", "HR", 60000);
    }

    @Test
    void testSaveEmployee() {
        // Arrange
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);  // Mock the save method
        
        // Act
        Employee savedEmployee = employeeServices.saveEmployee(employee);  // Call the service method

        // Assert
        assertNotNull(savedEmployee);
        assertEquals("John Doe", savedEmployee.getName());  // Verify that data is correct
        assertEquals("IT", savedEmployee.getDepartment());
        assertEquals(50000, savedEmployee.getSalary());
        verify(employeeRepository, times(1)).save(any(Employee.class));  // Verify save was called once
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));  // Mock the findById method

        // Act
        Employee foundEmployee = employeeServices.getEmployeeById(1L);  // Call the service method

        // Assert
        assertNotNull(foundEmployee);
        assertEquals("John Doe", foundEmployee.getName());  // Verify correct employee is fetched
        assertEquals("IT", foundEmployee.getDepartment());
        assertEquals(50000, foundEmployee.getSalary());
    }
    @Test
    void testUpdateEmployee() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));  // Mock findById
        when(employeeRepository.existsById(1L)).thenReturn(true);  // Ensure the employee exists
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);  // Mock save method

        // Act
        Employee updated = employeeServices.updateEmployee(1L, updatedEmployee);  // Call the service method

        // Assert
        assertNotNull(updated);
        assertEquals("John Doe", updated.getName());  
        assertEquals("HR", updated.getDepartment());
        assertEquals(60000, updated.getSalary());
        
        verify(employeeRepository, times(1)).findById(1L);  // Verify findById was called
        verify(employeeRepository, times(1)).save(any(Employee.class));  // Verify save was called
    }
    @Test
    void testDeleteEmployee() {
        // Arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee)); // Ensure the employee exists
        doNothing().when(employeeRepository).deleteById(1L);  // Mock delete method

        // Act
        employeeServices.deleteEmployee(1L);  // Call the service method

        // Assert
        verify(employeeRepository, times(1)).findById(1L); // Verify findById was called
        verify(employeeRepository, times(1)).deleteById(1L);  // Verify delete was called
    }



    @Test
    void testGetAllEmployees() {
        // Arrange
        Employee employee1 = new Employee("John Doe", "IT", 50000);
        Employee employee2 = new Employee("Jane Smith", "HR", 55000);
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee1, employee2));  // Mock findAll

        // Act
        var employees = employeeServices.getAllEmployees();  // Call the service method

        // Assert
        assertEquals(2, employees.size());  // Verify that the list contains 2 employees
        assertEquals("John Doe", employees.get(0).getName());
        assertEquals("Jane Smith", employees.get(1).getName());
    }
}
