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



@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/register")
    public ResponseEntity registerVehicle(@RequestBody VehicleRequestDto dto) throws Exception{
        try {
            Vehicle vehicle = VehicleMapper.toDomain(dto);
            VehicleResponse vehicleCreate= vehicleService.registerVehicle(vehicle);
            ApiResponse response = new ApiResponse<>(true, vehicleCreate);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<VehicleResponse>> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }  

    @GetMapping("/vehicles")
    public ResponseEntity<ApiResponse<List<VehicleResponse>>> getAllVeihcles() {
        try {
            List<VehicleResponse> vehicles = vehicleService.getAllVehicles();
            ApiResponse<List<VehicleResponse>> response = new ApiResponse<>(true, vehicles);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ApiResponse<List<VehicleResponse>> response = new ApiResponse<>(false, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
    }
    
}
