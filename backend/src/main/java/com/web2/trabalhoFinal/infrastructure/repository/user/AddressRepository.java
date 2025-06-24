package com.web2.trabalhoFinal.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.user.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}