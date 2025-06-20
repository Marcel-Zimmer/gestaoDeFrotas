package com.web2.trabalhoFinal.application.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.driver.DriverRequestDto;
import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.application.dto.trip.TripScheduleResponse;
import com.web2.trabalhoFinal.application.mapper.DriverMapper;
import com.web2.trabalhoFinal.domain.model.Driver.Driver;
import com.web2.trabalhoFinal.domain.service.DriverService;


@RestController
@RequestMapping("/driver")
public class DriverController {

    private DriverService driverService;

    public DriverController(DriverService driverService){
        this.driverService = driverService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<DriverResponse>> registerDriver(@RequestBody DriverRequestDto dto) {
        try {
            Driver driver = DriverMapper.toDomain(dto);
            DriverResponse createDriver = driverService.createDriver(driver);
            String sucess = "Motorista criado com sucesso";
            ApiResponse<DriverResponse> response = new ApiResponse<>(true, sucess,createDriver);
            return ResponseEntity.ok(response);    

        }catch (Exception e) {
            ApiResponse<DriverResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DriverResponse>>> getMethodName() {
        try {
            List<DriverResponse> drivers = driverService.getAllDrivers();
            String successMessage = "Motoristas recuperados";
            ApiResponse<List<DriverResponse>> response = new ApiResponse<>(true, successMessage,drivers);
            return ResponseEntity.ok(response);  

        } catch (Exception e) {
            ApiResponse<List<DriverResponse>> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    

}