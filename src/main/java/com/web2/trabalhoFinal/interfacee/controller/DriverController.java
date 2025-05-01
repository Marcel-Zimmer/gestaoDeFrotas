package com.web2.trabalhoFinal.interfacee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web2.trabalhoFinal.domain.model.Driver;
import com.web2.trabalhoFinal.domain.service.DriverService;
import com.web2.trabalhoFinal.infrastructure.entity.DriverEntity;

@RestController
@RequestMapping("/register")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }
    
    @PostMapping("/driver")
    public ResponseEntity<DriverEntity> cadastrarMotorista(@RequestBody Driver motorista) {
        DriverEntity motoristaCriadoEntity = driverService.createDriver(motorista);
        return ResponseEntity.status(HttpStatus.CREATED).body(motoristaCriadoEntity);
    }

}