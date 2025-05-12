package com.web2.trabalhoFinal.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web2.trabalhoFinal.application.dto.maintenance.MaintenanceResponse;
import com.web2.trabalhoFinal.domain.model.maintenance.Maintenance;
import com.web2.trabalhoFinal.infrastructure.entity.maintenance.MaintenanceEntity;
import com.web2.trabalhoFinal.infrastructure.entity.maintenance.TypeMaintenanceEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.StatusVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.maintenance.MaintenanceRepository;
import com.web2.trabalhoFinal.infrastructure.repository.maintenance.TypeMaintenanceRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.StatusVehicleRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.VehicleRepository;

@Service 
public class MaintenanceService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    TypeMaintenanceRepository typeMaintenanceRepository;
    @Autowired
    MaintenanceRepository maintenanceRepository;
    @Autowired
    StatusVehicleRepository statusVehicleRepository;

    public MaintenanceResponse registerMaintenance(Maintenance maintenance) {
        VehicleEntity vehicleEntity = vehicleRepository.getReferenceById(maintenance.getIdVehicle().getValue());

        TypeMaintenanceEntity typeMaintenanceEntity = typeMaintenanceRepository.findByTypeMaintenance(maintenance.getType().getTypeMaintenance())
        .orElseThrow(() -> new IllegalArgumentException("Tipo de manutenção inválido"));

        MaintenanceEntity maintenanceEntity = new MaintenanceEntity(vehicleEntity, typeMaintenanceEntity, maintenance.getDate().getValue(),
        maintenance.getDescription().getValue(), maintenance.getPrice().getValue(), maintenance.getCurrentMileage().getValue());
        
        StatusVehicleEntity statusVehicleEntity = statusVehicleRepository.findByStatusVehicle("EM_MANUTENCAO")
        .orElseThrow(() -> new IllegalArgumentException("Tipo de manutenção inválido"));

        vehicleEntity.setStatusVehicleEntity(statusVehicleEntity);

        maintenanceRepository.save(maintenanceEntity); 
        return new MaintenanceResponse(true, "manutenção criada com sucesso", maintenanceEntity.getId());
    }

}
