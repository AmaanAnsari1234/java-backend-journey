package com.example.employee_api;

import com.example.employee_api.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.add(new Employee(1L, "Amaan", "IT"));
        employees.add(new Employee(2L, "Sara", "HR"));
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }
}