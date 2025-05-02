package com.web2.trabalhoFinal.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web2.trabalhoFinal.application.dto.DriverRequestDto;
import com.web2.trabalhoFinal.application.mapper.DriverMapper;
import com.web2.trabalhoFinal.domain.model.Driver;
import com.web2.trabalhoFinal.domain.service.DriverService;

@RestController
@RequestMapping("/register")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }
    
    @PostMapping("/driver")
    public ResponseEntity<Void> driverRegister(@RequestBody DriverRequestDto dto) {
        Driver driver = DriverMapper.toDomain(dto);
        boolean driverIsCreate = driverService.createDriver(driver);
        if(driverIsCreate){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}