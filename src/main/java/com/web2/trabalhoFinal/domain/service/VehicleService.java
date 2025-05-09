package com.web2.trabalhoFinal.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.user.LoginResponse;
import com.web2.trabalhoFinal.application.dto.vehicle.VehicleResponse;
import com.web2.trabalhoFinal.domain.model.Vehicle.Vehicle;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.ModelVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.StatusVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.TypeVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.YearVehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.ModelVehicleRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.StatusVehicleRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.TypeVehicleRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.VehicleRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.YearVehicleRepository;


@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelVehicleRepository modelVehicleRepository;
    @Autowired
    private StatusVehicleRepository statusVehicleRepository;
    @Autowired
    private TypeVehicleRepository typeVehicleRepository;
    @Autowired
    private YearVehicleRepository yearVehicleRepository;

    public VehicleResponse registerVehicle(Vehicle vehicle) {

        try {
            ModelVehicleEntity modelVehicleEntity = new ModelVehicleEntity(vehicle.getModelVehicle().getModel());
            StatusVehicleEntity statusVehicleEntity = new StatusVehicleEntity(vehicle.getStatusVehicle().getStatus());
            TypeVehicleEntity typeVehicleEntity = new TypeVehicleEntity(vehicle.getTypeVehicle().getType());
            YearVehicleEntity yearVehicleEntity = new YearVehicleEntity(vehicle.getYearVehicle().getYear());
            VehicleEntity vehicleEntity = new VehicleEntity(vehicle.getLicencePlate().getLicence(),vehicle.getCurrentMileage().getValue(),modelVehicleEntity, statusVehicleEntity, typeVehicleEntity, yearVehicleEntity);
            modelVehicleRepository.save(modelVehicleEntity);
            statusVehicleRepository.save(statusVehicleEntity);
            typeVehicleRepository.save(typeVehicleEntity);
            yearVehicleRepository.save(yearVehicleEntity);
            vehicleRepository.save(vehicleEntity);
            return new VehicleResponse(true, "veiculo criado",vehicleEntity.getId());
        } catch (Exception e) {
            return new VehicleResponse(false, e.getMessage());
        }
    }

}
