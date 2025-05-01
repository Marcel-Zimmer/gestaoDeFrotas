package com.web2.trabalhoFinal.interfacee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.domain.model.Driver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
@RequestMapping("/api")

public class HelloController {
    

    public static void main(String[] args) {
        SpringApplication.run(HelloController.class, args);
    }

    @PostMapping("/motoristas")
    public String cadastrarMotorista(@RequestBody Driver motorista) {
        return "Motorista cadastrado com sucesso: ";
    }
    
}    
    



