package com.example.employee_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public  class HelloController{

    @GetMapping("/hello")
    public String hello(){
        return "Hello, Spring Boooot";
    }

    @GetMapping("/goodbye")
    public String goodbye(){
        return "Goodbye, Spring Boot!";
    }
}