package com.web2.trabalhoFinal.infrastructure.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email")
    UserEntity findByEmail(@Param("email") String email);

    UserEntity findByName(String name);

    public boolean existsByEmail(String value);

    public boolean existsByCpf(String value);

}
