package com.web2.trabalhoFinal.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.domain.model.Driver;
import com.web2.trabalhoFinal.domain.service.DriverService;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;



/* 
@RestController
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    } 
    
    @PostMapping("/user")
    public ResponseEntity<LoginEntity> userLogin(@RequestBody Driver motorista) {
        DriverEntity motoristaCriadoEntity = driverService.createDriver(motorista);
        return ResponseEntity.status(HttpStatus.CREATED).body(motoristaCriadoEntity);
    }    
}
*/