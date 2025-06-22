
package com.web2.trabalhoFinal.infrastructure.repository.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.driver.CnhEntity;

@Repository
public interface CnhRepository extends JpaRepository<CnhEntity, Long> {

    public CnhEntity findByCnh(String value);

}