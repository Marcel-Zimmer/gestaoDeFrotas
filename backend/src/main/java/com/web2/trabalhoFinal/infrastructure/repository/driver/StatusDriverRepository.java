package com.web2.trabalhoFinal.infrastructure.repository.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.driver.StatusDriverEntity;


@Repository
public interface StatusDriverRepository extends JpaRepository<StatusDriverEntity, Long> {

    public StatusDriverEntity findByStatus(String status);

    
}
