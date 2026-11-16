package com.example.employee_api.service;

import com.example.employee_api.model.Employee;
import com.example.employee_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import com.example.employee_api.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee add(Employee employee) {
        employee.setId(null);
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, Employee updated) {
        Employee existing = getById(id);

            existing.setName(updated.getName());
            existing.setDepartment(updated.getDepartment());
            return employeeRepository.save(existing);

    }

    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }
}