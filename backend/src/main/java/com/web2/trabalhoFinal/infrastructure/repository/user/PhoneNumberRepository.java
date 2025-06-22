package com.web2.trabalhoFinal.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.driver.PhoneNumberEntity;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumberEntity, Long> {

    public PhoneNumberEntity findByPhoneNumber(String phoneNumber);

}