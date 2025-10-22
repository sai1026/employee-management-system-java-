package com.example.Employee.controller.test;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.Employee.controller.EmployeeController;
import com.example.Employee.model.Employee;
import com.example.Employee.service.EmployeeServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
@ExtendWith(MockitoExtension.class)

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServices employeeServices;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void testCreateEmployee() throws Exception {
        Employee employee = new Employee("John Doe", "IT", 60000);
        when(employeeServices.saveEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.department").value("IT"))
                .andExpect(jsonPath("$.salary").value(60000));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        List<Employee> employees = Arrays.asList(
                new Employee("John Doe", "IT", 60000),
                new Employee("Jane Doe", "HR", 50000)
        );
        when(employeeServices.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Doe"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        Employee employee = new Employee("John Doe", "IT", 60000);
        when(employeeServices.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee updatedEmployee = new Employee("John Updated", "IT", 70000);
        when(employeeServices.updateEmployee(eq(1L), any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"))
                .andExpect(jsonPath("$.salary").value(70000));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        doNothing().when(employeeServices).deleteEmployee(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isOk());
    }
}
