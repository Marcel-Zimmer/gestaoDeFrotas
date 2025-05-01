package com.web2.trabalhoFinal.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.DriverEntity;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
}
