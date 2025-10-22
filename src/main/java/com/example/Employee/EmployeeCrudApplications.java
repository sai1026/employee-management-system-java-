package com.example.Employee;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class EmployeeCrudApplications {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeCrudApplications.class, args);
        System.out.println("🚀 Employee CRUD Application Started Successfully!");
    }
}
