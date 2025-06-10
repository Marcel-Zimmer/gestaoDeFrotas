package com.web2.trabalhoFinal.application.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web2.trabalhoFinal.application.dto.maintenance.MaintenanceRequestDto;
import com.web2.trabalhoFinal.application.dto.maintenance.MaintenanceResponse;
import com.web2.trabalhoFinal.application.mapper.MaintenanceMapper;
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
    public MaintenanceResponse registerRefuling(@RequestBody MaintenanceRequestDto dto) throws Exception{
        try {
        Maintenance maintence = MaintenanceMapper.toDomain(dto);
        MaintenanceResponse maintenceCreate= maintenanceService.registerMaintenance(maintence);
        return maintenceCreate; 
        } catch (Exception e) {
           return new MaintenanceResponse(false, e.getMessage());
        }

    }          
}
