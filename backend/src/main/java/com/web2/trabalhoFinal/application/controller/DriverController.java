package com.web2.trabalhoFinal.application.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.driver.DriverRequestDto;
import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.application.mapper.DriverMapper;
import com.web2.trabalhoFinal.domain.model.Driver.Driver;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
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

        }catch (ResourceNotFoundException e) {
            ApiResponse<DriverResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    
        } catch (Exception e) {
            ApiResponse<DriverResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/drivers")
    public ResponseEntity<ApiResponse<List<DriverResponse>>> getAllDrivers() {
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
    @GetMapping("/disponibleDrivers")
    public ResponseEntity<ApiResponse<List<DriverResponse>>> getAllDisponibleDrivers() {
        try {
            List<DriverResponse> drivers = driverService.getAllDisponibleDrivers();
            String successMessage = "Motoristas disponiveis recuperados";
            ApiResponse<List<DriverResponse>> response = new ApiResponse<>(true, successMessage,drivers);
            return ResponseEntity.ok(response);  

        } catch (Exception e) {
            ApiResponse<List<DriverResponse>> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }   
     
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<DriverResponse>> updateDriver (@PathVariable Long id,@RequestBody DriverRequestDto dto) {
        try {
            Driver driver = DriverMapper.toDomain(dto);
            DriverResponse responseDriver = driverService.updateDriver(id, driver);
            String successMessage = "Motorista atualizado";
            ApiResponse<DriverResponse> response = new ApiResponse<>(true, successMessage,responseDriver);
            return ResponseEntity.status(HttpStatus.OK).body(response);          

        }catch (ResourceNotFoundException e) {
            ApiResponse<DriverResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    
            
        } catch (Exception e) {
            ApiResponse<DriverResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
     @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDriver(@PathVariable Long id) {
        try {
            ApiResponse<Void> response = driverService.deleteDriver(id);
            return ResponseEntity.ok(response);
        
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar o motorista: " + e.getMessage();
            ApiResponse<Void> response = new ApiResponse<>(false, errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }      

}