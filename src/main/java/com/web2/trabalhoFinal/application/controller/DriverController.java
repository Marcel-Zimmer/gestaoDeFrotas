package com.web2.trabalhoFinal.application.controller;

import org.springframework.web.bind.annotation.*;

import com.web2.trabalhoFinal.application.dto.driver.DriverRequestDto;
import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.application.mapper.DriverMapper;
import com.web2.trabalhoFinal.domain.model.driver.Driver;
import com.web2.trabalhoFinal.domain.service.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }
    
    @PostMapping("/register")
    public DriverResponse registerDriver(@RequestBody DriverRequestDto dto) {
        try {
            Driver driver = DriverMapper.toDomain(dto);
            DriverResponse createDriver = driverService.createDriver(driver);
            return createDriver;
        } catch (Exception e) {
            return new DriverResponse(false, "erro ao criar" + e.getMessage());
        }
    }

}