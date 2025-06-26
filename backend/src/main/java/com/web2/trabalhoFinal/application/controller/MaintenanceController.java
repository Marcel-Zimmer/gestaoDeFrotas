package com.web2.trabalhoFinal.application.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.maintenance.MaintenanceRequestDto;
import com.web2.trabalhoFinal.application.dto.maintenance.MaintenanceResponse;
import com.web2.trabalhoFinal.application.mapper.MaintenanceMapper;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
import com.web2.trabalhoFinal.domain.model.maintenance.Maintenance;
import com.web2.trabalhoFinal.domain.service.MaintenanceService;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    private MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService){
        this.maintenanceService = maintenanceService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MaintenanceResponse>> registerRefuling(@RequestBody MaintenanceRequestDto dto) throws Exception{
        try {
        Maintenance maintence = MaintenanceMapper.toDomain(dto);
        var maintenceCreate= maintenanceService.registerMaintenance(maintence);
        String successMessage = "Abastecimento registrado";
        ApiResponse<MaintenanceResponse> response = new ApiResponse<>(true, successMessage,maintenceCreate);
        return ResponseEntity.ok(response); 
        }catch (ResourceNotFoundException e) {
            ApiResponse<MaintenanceResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    
        } catch (Exception e) {
            var response = new ApiResponse<MaintenanceResponse>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }    
    
    @GetMapping("/maintenances")
    public ResponseEntity<ApiResponse<List<MaintenanceResponse>>> getAllMaintenances() {
        try {
            List<MaintenanceResponse> maintences = maintenanceService.getAllMaintenances();
            String successMessage = "Manutenções recuperadas";
            ApiResponse<List<MaintenanceResponse>> response = new ApiResponse<>(true, successMessage,maintences);
            return ResponseEntity.ok(response);
         } catch (Exception e) {
            ApiResponse<List<MaintenanceResponse>> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteMaintenance(@PathVariable Long id) {
        try {
            ApiResponse<Void> response = maintenanceService.deleteMaintenance(id);
            return ResponseEntity.ok(response);
        
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar a manutenção: " + e.getMessage();
            ApiResponse<Void> response = new ApiResponse<>(false, errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }            
}
