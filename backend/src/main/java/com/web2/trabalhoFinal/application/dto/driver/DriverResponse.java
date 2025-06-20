package com.web2.trabalhoFinal.application.dto.driver;

import com.web2.trabalhoFinal.infrastructure.entity.driver.AddressEntity;

public class DriverResponse {
    private Long driverId;
    private String nameDriver;
    private String cpf;
    private String cnh;
    private String email;
    private AddressEntity address;
    
    public DriverResponse(Long driverId, String nameDriver, String cpf, String cnh, String email,
            AddressEntity address) {
        this.driverId = driverId;
        this.nameDriver = nameDriver;
        this.cpf = cpf;
        this.cnh = cnh;
        this.email = email;
        this.address = address;
    }

    public DriverResponse(Long driverId) {
        this.driverId = driverId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public String getEmail() {
        return email;
    }

    public AddressEntity getAddress() {
        return address;
    }



}
