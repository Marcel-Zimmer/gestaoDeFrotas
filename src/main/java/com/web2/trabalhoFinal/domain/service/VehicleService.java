package com.web2.trabalhoFinal.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.web2.trabalhoFinal.application.dto.VehicleResponse;
import com.web2.trabalhoFinal.domain.model.Vehicle.Vehicle;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.ModelVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.StatusVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.TypeVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.YearVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.VehicleRepository;

public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleResponse registerVehicle(Vehicle vehicle) {

        try {
            ModelVehicleEntity modelVehicleEntity = new ModelVehicleEntity(vehicle.getModelVehicle().getModel());
            StatusVehicleEntity statusVehicleEntity = new StatusVehicleEntity(vehicle.getStatusVehicle().getStatus());
            TypeVehicleEntity typeVehicleEntity = new TypeVehicleEntity(vehicle.getTypeVehicle().getType());
            YearVehicleEntity yearVehicleEntity = new YearVehicleEntity(vehicle.getYearVehicle().getYear());
            VehicleEntity vehicleEntity = new VehicleEntity(vehicle.getLicencePlate().getLicence(),vehicle.getCurrentMileage().getValue(),modelVehicleEntity, statusVehicleEntity, typeVehicleEntity, yearVehicleEntity);
            vehicleRepository.save(vehicleEntity);
        } catch (Exception e) {
        }
        return null;
    }

}
