package com.web2.trabalhoFinal.infrastructure.entity.driver;


import com.web2.trabalhoFinal.domain.model.Driver.StatusDriver;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "driver")
public class DriverEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cnh_id", referencedColumnName = "id", nullable = false)
    private CnhEntity cnh;

    @Enumerated(EnumType.STRING) 
    @Column(name = "status", nullable = false)
    private StatusDriver status;
    
    public DriverEntity() {}

    public DriverEntity(Long id) {
        this.id = id;
    }

    public DriverEntity(Long id, UserEntity user, CnhEntity cnh, StatusDriver status) {
        this.id = id;
        this.user = user;
        this.cnh = cnh;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CnhEntity getCnh() {
        return cnh;
    }

    public void setCnh(CnhEntity cnh) {
        this.cnh = cnh;
    }

    public StatusDriver getStatus() {
        return status;
    }

    public void setStatus(StatusDriver status) {
        this.status = status;
    }



}