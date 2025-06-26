package com.web2.trabalhoFinal.domain.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.maintenance.MaintenanceResponse;
import com.web2.trabalhoFinal.domain.model.maintenance.Maintenance;
import com.web2.trabalhoFinal.infrastructure.entity.maintenance.MaintenanceEntity;
import com.web2.trabalhoFinal.infrastructure.entity.maintenance.TypeMaintenanceEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.maintenance.MaintenanceRepository;
import com.web2.trabalhoFinal.infrastructure.repository.maintenance.TypeMaintenanceRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.VehicleRepository;

@Service 
public class MaintenanceService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    TypeMaintenanceRepository typeMaintenanceRepository;
    @Autowired
    MaintenanceRepository maintenanceRepository;

    public MaintenanceResponse registerMaintenance(Maintenance maintenance) {
        VehicleEntity vehicleEntity = vehicleRepository.getReferenceById(maintenance.getIdVehicle().getValue());

        TypeMaintenanceEntity typeMaintenanceEntity = typeMaintenanceRepository.findByTypeMaintenance(maintenance.getType().getTypeMaintenance());
        if(typeMaintenanceEntity == null){
            typeMaintenanceEntity = new TypeMaintenanceEntity(maintenance.getType().getTypeMaintenance());
            typeMaintenanceRepository.save(typeMaintenanceEntity);
        }  

        MaintenanceEntity maintenanceEntity = new MaintenanceEntity(vehicleEntity, typeMaintenanceEntity, maintenance.getDate().getValue(),
        maintenance.getDescription().getValue(), maintenance.getPrice().getValue(), maintenance.getCurrentMileage().getValue());

        maintenanceRepository.save(maintenanceEntity); 
        return new MaintenanceResponse(maintenanceEntity.getId());
    }

    public List<MaintenanceResponse> getAllMaintenances() {
        List<MaintenanceEntity> maintenances = maintenanceRepository.findAll();
        List<MaintenanceResponse> response = new ArrayList<>(); 

        for (MaintenanceEntity maintenance : maintenances) {
            MaintenanceResponse maintenanceResponse = new MaintenanceResponse(maintenance.getId(),maintenance.getVehicle(), maintenance.getDateMaintenance(), maintenance.getPriceMaintenance(),maintenance.getCurrentMileage(),maintenance.getDescriptionMaintenance(),maintenance.getTypeMaintenanceEntity().getTypeMaintenance());
             response.add(maintenanceResponse);
        }
        return response;
    }

    public ApiResponse<Void> deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
        String successMessage = "Manutenção deletada";
        return new ApiResponse<>(true, successMessage, null);
    }

}
