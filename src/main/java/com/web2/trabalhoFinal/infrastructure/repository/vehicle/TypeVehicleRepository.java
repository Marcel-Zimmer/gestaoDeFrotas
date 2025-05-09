package com.web2.trabalhoFinal.infrastructure.repository.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.TypeVehicleEntity;

@Repository
public interface TypeVehicleRepository extends JpaRepository<TypeVehicleEntity, Long> {

}