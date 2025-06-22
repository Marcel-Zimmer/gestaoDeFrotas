package com.web2.trabalhoFinal.infrastructure.repository.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.driver.CpfEntity;

@Repository
public interface CpfRepository extends JpaRepository<CpfEntity, Long> {

    public CpfEntity findByCpf(String value);

}