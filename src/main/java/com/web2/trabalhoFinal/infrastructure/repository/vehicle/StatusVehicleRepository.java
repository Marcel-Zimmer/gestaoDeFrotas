package com.web2.trabalhoFinal.infrastructure.repository.vehicle;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.StatusVehicleEntity;

@Repository
public interface StatusVehicleRepository extends JpaRepository<StatusVehicleEntity, Long> {
    Optional<StatusVehicleEntity> findByStatusVehicle(String statusVehicle);
}