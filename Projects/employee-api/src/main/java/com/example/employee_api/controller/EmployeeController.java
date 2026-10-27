package com.example.employee_api.controller;

import com.example.employee_api.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return ResponseEntity.ok(employee);
            }
        }
        return ResponseEntity.notFound().build();
    }

    private long nextId = 4;
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
}