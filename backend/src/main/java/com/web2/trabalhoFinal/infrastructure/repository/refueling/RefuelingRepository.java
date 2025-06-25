package com.web2.trabalhoFinal.infrastructure.repository.refueling;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.refueling.RefuelingEntity;

@Repository
public interface RefuelingRepository extends JpaRepository<RefuelingEntity, Long> {
    @Query("SELECT r FROM RefuelingEntity r " +
           "JOIN FETCH r.vehicle v " +
           "JOIN FETCH r.typeRefueling tr")
    List<RefuelingEntity> findAllWithVehicleAndTypeDetails();
}