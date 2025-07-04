package com.web2.trabalhoFinal.infrastructure.repository.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.vehicle.StatusVehicleEntity;

@Repository
public interface StatusVehicleRepository extends JpaRepository<StatusVehicleEntity, Long> {
    StatusVehicleEntity findByStatusVehicle(String statusVehicle);

}