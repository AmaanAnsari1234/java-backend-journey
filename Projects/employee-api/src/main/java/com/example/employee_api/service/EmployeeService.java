package com.example.employee_api.service;

import com.example.employee_api.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private long nextId = 4;

    public EmployeeService() {
        employees.add(new Employee(1L, "Amaan", "IT"));
        employees.add(new Employee(2L, "Sara", "HR"));
        employees.add(new Employee(3L, "Ali", "Finance"));
    }

    public List<Employee> getAll() {
        return employees;
    }

    public Optional<Employee> getById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    public Employee add(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    public Optional<Employee> update(Long id, Employee updated) {
        Optional<Employee> existing = getById(id);
        existing.ifPresent(employee -> {
            employee.setName(updated.getName());
            employee.setDepartment(updated.getDepartment());
        });
        return existing;
    }

    public boolean delete(Long id) {
        return employees.removeIf(employee -> employee.getId().equals(id));
    }
}