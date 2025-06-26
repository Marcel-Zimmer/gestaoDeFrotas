package com.web2.trabalhoFinal.domain.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.vehicle.VehicleResponse;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
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

import jakarta.transaction.Transactional;

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
        if(vehicleRepository.findByLicencePlate(vehicle.getLicencePlate().getLicence()) != null){
            throw new ResourceNotFoundException("Placa já cadastrada");
        }

        ModelVehicleEntity modelVehicleEntity = modelVehicleRepository.findByModelVehicle(vehicle.getModelVehicle().getModel());
        if(modelVehicleEntity == null){
            modelVehicleEntity = new ModelVehicleEntity(vehicle.getModelVehicle().getModel());
            modelVehicleRepository.save(modelVehicleEntity);
        }

        StatusVehicleEntity statusVehicleEntity = statusVehicleRepository.findByStatusVehicle(vehicle.getStatusVehicle().getStatus());
        if(statusVehicleEntity == null){
            statusVehicleEntity = new StatusVehicleEntity(vehicle.getStatusVehicle().getStatus());
            statusVehicleRepository.save(statusVehicleEntity);
        }      
        
            
        TypeVehicleEntity typeVehicleEntity = typeVehicleRepository.findByTypeVehicle(vehicle.getTypeVehicle().getType());
        if(typeVehicleEntity == null){
            typeVehicleEntity = new TypeVehicleEntity(vehicle.getTypeVehicle().getType());
            typeVehicleRepository.save(typeVehicleEntity);
        }
            
        YearVehicleEntity yearVehicleEntity = yearVehicleRepository.findByYearVehicle(vehicle.getYearVehicle().getYear());
        if(yearVehicleEntity == null){
            yearVehicleEntity = new YearVehicleEntity(vehicle.getYearVehicle().getYear());
            yearVehicleRepository.save(yearVehicleEntity);
        }
        VehicleEntity vehicleEntity = new VehicleEntity(vehicle.getLicencePlate().getLicence(),vehicle.getCurrentMileage().getValue(),modelVehicleEntity, statusVehicleEntity, typeVehicleEntity, yearVehicleEntity);
        vehicleRepository.save(vehicleEntity);
        return new VehicleResponse(vehicleEntity.getId());
    }

    public List<VehicleResponse> getAllVehicles(){
        List<VehicleEntity> vehicles = vehicleRepository.findAllWithDetails();
        List<VehicleResponse> response = new ArrayList<>(); 

        for (VehicleEntity vehicle : vehicles) {
            VehicleResponse vehicleResponse = new VehicleResponse(vehicle.getId(), 
            vehicle.getLicencePlate(), vehicle.getModelVehicleEntity().getModelVehicle(), vehicle.getTypeVehicleEntity().getTypeVehicle(),
             vehicle.getYearVehicleEntity().getYearVehicle(), String.valueOf(vehicle.getCurrentMileage()), vehicle.getStatusVehicleEntity().getStatusVehicle());
             response.add(vehicleResponse);
        }
        return response;
    }

    public ApiResponse<VehicleResponse> updateVehicle(Long id, Vehicle vehicle) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("id inválido"));

        StatusVehicleEntity statusVehicleEntity = statusVehicleRepository.findByStatusVehicle(vehicle.getStatusVehicle().getStatus());
        if(statusVehicleEntity == null){
            statusVehicleEntity = new StatusVehicleEntity(vehicle.getStatusVehicle().getStatus());
            statusVehicleRepository.save(statusVehicleEntity);
        } 

        ModelVehicleEntity modelVehicleEntity = modelVehicleRepository.findByModelVehicle(vehicle.getModelVehicle().getModel());
        if(modelVehicleEntity == null){
            modelVehicleEntity = new ModelVehicleEntity(vehicle.getModelVehicle().getModel());
            modelVehicleRepository.save(modelVehicleEntity);
        }

        TypeVehicleEntity typeVehicleEntity = typeVehicleRepository.findByTypeVehicle(vehicle.getTypeVehicle().getType());
        if(typeVehicleEntity == null){
            typeVehicleEntity = new TypeVehicleEntity(vehicle.getTypeVehicle().getType());
            typeVehicleRepository.save(typeVehicleEntity);
        }
            
        YearVehicleEntity yearVehicleEntity = yearVehicleRepository.findByYearVehicle(vehicle.getYearVehicle().getYear());
        if(yearVehicleEntity == null){
            yearVehicleEntity = new YearVehicleEntity(vehicle.getYearVehicle().getYear());
            yearVehicleRepository.save(yearVehicleEntity);
        }

        VehicleEntity plate = vehicleRepository.findByLicencePlate(vehicle.getLicencePlate().getLicence());
        if(plate != null && plate.getId() != id) {
            throw new ResourceNotFoundException("Placa já cadastrada");
        }
        vehicleEntity.setLicencePlate(vehicle.getLicencePlate().getLicence());
        vehicleEntity.setCurrentMileage(vehicle.getCurrentMileage().getValue());
        vehicleEntity.setModelVehicleEntity(modelVehicleEntity);
        vehicleEntity.setTypeVehicleEntity(typeVehicleEntity);
        vehicleEntity.setStatusVehicleEntity(statusVehicleEntity);
        vehicleRepository.save(vehicleEntity);
        
        String successMessage = "Veiculo atualizado";
        ApiResponse<VehicleResponse> response = new ApiResponse<>(true, successMessage, new VehicleResponse(id));
        return response; 
    }

    @Transactional
    public ApiResponse<Void> deleteVehicle(Long id) {
        VehicleEntity vehicle = vehicleRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("id inválido"));;

        StatusVehicleEntity statusVehicleEntity = statusVehicleRepository.findByStatusVehicle("INATIVO");
        if(statusVehicleEntity == null){
            statusVehicleEntity = new StatusVehicleEntity("INATIVO");
            statusVehicleRepository.save(statusVehicleEntity);
        } 
        vehicle.setStatusVehicleEntity(statusVehicleEntity);
        String successMessage = "Veiculo deletado";
        return new ApiResponse<>(true, successMessage, null);
    }

}
