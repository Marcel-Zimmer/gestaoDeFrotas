package com.web2.trabalhoFinal.infrastructure.repository.maintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.maintenance.TypeMaintenanceEntity;

@Repository
public interface TypeMaintenanceRepository extends JpaRepository<TypeMaintenanceEntity, Long> {
    TypeMaintenanceEntity findByTypeMaintenance(String typeMaintenance);
}