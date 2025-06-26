package com.web2.trabalhoFinal.infrastructure.repository.vehicle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    @Query("SELECT v FROM VehicleEntity v " +
           "JOIN FETCH v.modelVehicleEntity " +
           "JOIN FETCH v.typeVehicleEntity " +
           "JOIN FETCH v.yearVehicleEntity " +
           "JOIN FETCH v.statusVehicleEntity")
    List<VehicleEntity> findAllWithDetails();

    VehicleEntity findByLicencePlate(String licence);

    @Query("SELECT v FROM VehicleEntity v " +
           "JOIN FETCH v.modelVehicleEntity " +
           "JOIN FETCH v.typeVehicleEntity " +
           "JOIN FETCH v.yearVehicleEntity " +
           "JOIN FETCH v.statusVehicleEntity s " +   
           "WHERE s.statusVehicle = 'DISPONIVEL'")  
    List<VehicleEntity> findAllAvailableWithDetails();

}