package com.web2.trabalhoFinal.infrastructure.repository.refueling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.refueling.TypeRefuelingEntity;

@Repository
public interface TypeRefuelingRepository extends JpaRepository<TypeRefuelingEntity, Long> {

    TypeRefuelingEntity findByTypeRefueling(String typeRefueling);

}
