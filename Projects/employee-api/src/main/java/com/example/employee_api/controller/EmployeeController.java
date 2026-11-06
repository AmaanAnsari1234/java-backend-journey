package com.example.employee_api.controller;

import com.example.employee_api.dto.EmployeeRequest;
import com.example.employee_api.model.Employee;
import com.example.employee_api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@Valid @RequestBody EmployeeRequest request) {
        Employee saved = employeeService.add(toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    private Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setDepartment(request.getDepartment());
        return employee;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee updated) {
        return employeeService.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}