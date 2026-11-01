package com.example.employee_api.model;

import jakarta.persistence.Entity;                    // NEW
import jakarta.persistence.GeneratedValue;            // NEW
import jakarta.persistence.GenerationType;            // NEW
import jakarta.persistence.Id;import javax.annotation.processing.Generated;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;

    // NEW: empty constructor
    public Employee() {
    }

    public Employee(Long id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    // NEW: setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}