package com.example.employee_api.dto;

public class EmployeeResponse {
    private  long id;
    private  String name;
    private String department;

    public EmployeeResponse(Long id, String name, String department){
        this.department=department;
        this.id= id;
        this.name = name;
    }

    public  Long getId(){return id;}
    public  String getName(){return name;}
    public  String getDepartment(){return department;}
}
