package com.web2.trabalhoFinal.infrastructure.repository.refueling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.refueling.RefuelingEntity;

@Repository
public interface RefuelingRepository extends JpaRepository<RefuelingEntity, Long> {

}