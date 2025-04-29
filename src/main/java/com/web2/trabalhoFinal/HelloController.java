package com.web2.trabalhoFinal;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
@RequestMapping("/api")

public class HelloController {
    

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class, args);
    }

    @GetMapping("/hello")

    public String hello() {
    
    return "Hello, World!";
    
    }    
    

}

