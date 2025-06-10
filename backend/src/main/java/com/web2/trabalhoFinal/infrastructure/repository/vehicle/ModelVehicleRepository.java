package com.web2.trabalhoFinal.infrastructure.repository.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.vehicle.ModelVehicleEntity;

@Repository
public interface ModelVehicleRepository extends JpaRepository<ModelVehicleEntity, Long> {
    ModelVehicleEntity findByModelVehicle(String modelVehicle);
}