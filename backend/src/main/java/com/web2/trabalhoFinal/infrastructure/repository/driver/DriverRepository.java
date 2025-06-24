package com.web2.trabalhoFinal.infrastructure.repository.driver;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.domain.model.Driver.StatusDriver;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
    
    @Query("SELECT d FROM DriverEntity d JOIN FETCH d.user u JOIN FETCH d.cnh c")
    List<DriverEntity> findAllWithDetails();

    @Query("SELECT d FROM DriverEntity d JOIN FETCH d.user u JOIN FETCH d.cnh c WHERE d.status = :status")
    List<DriverEntity> findByStatusWithDetails(@Param("status") StatusDriver status);

    
}
