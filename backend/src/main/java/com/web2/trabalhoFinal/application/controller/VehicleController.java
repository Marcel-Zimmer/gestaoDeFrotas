package com.web2.trabalhoFinal.application.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.vehicle.VehicleRequestDto;
import com.web2.trabalhoFinal.application.dto.vehicle.VehicleResponse;
import com.web2.trabalhoFinal.application.mapper.VehicleMapper;
import com.web2.trabalhoFinal.domain.model.Vehicle.Vehicle;
import com.web2.trabalhoFinal.domain.service.VehicleService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;






@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<VehicleResponse>> registerVehicle(@RequestBody VehicleRequestDto dto) throws Exception{
        try {
            Vehicle vehicle = VehicleMapper.toDomain(dto);
            VehicleResponse vehicleCreate= vehicleService.registerVehicle(vehicle);
            String successMessage = "Veículo cadastrado com sucesso!";
            ApiResponse<VehicleResponse> response = new ApiResponse<>(true, successMessage, vehicleCreate);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            ApiResponse<VehicleResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }  

    @GetMapping("/vehicles")
    public ResponseEntity<ApiResponse<List<VehicleResponse>>> getAllVeihcles() {
        try {
            List<VehicleResponse> vehicles = vehicleService.getAllVehicles();
            String successMessage = "Veiculos recuperados";
            ApiResponse<List<VehicleResponse>> response = new ApiResponse<>(true, successMessage,vehicles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<VehicleResponse>> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
    }

    @PutMapping("update/{id}")
    public Void updateVehicle (@PathVariable Long id,@RequestBody VehicleRequestDto dto) {
        System.out.println(id);
        Vehicle vehicle = VehicleMapper.toDomain(dto);
        return null;
    }
    
}
