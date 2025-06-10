package com.web2.trabalhoFinal.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DddNumberEntity;

@Repository
public interface DddNumberRepository extends JpaRepository<DddNumberEntity, Long> {
    DddNumberEntity findByDddNumber(String dddNumber);
}