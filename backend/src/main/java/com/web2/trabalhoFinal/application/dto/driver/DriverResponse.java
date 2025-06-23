package com.web2.trabalhoFinal.application.dto.driver;

import java.time.LocalDate;

import com.web2.trabalhoFinal.infrastructure.entity.driver.AddressEntity;

public class DriverResponse {
    private Long driverId;
    private String nameDriver;
    private String cpf;
    private String cnh;
    private LocalDate cnhExpiration;
    private String email;
    private String ddd;
    private String phoneNumber;
    private AddressEntity address;
    
    public DriverResponse(Long driverId, String nameDriver, String cpf, String cnh, LocalDate cnhExpiration, String email,
            String ddd, String phoneNumber, AddressEntity address) {
        this.driverId = driverId;
        this.nameDriver = nameDriver;
        this.cpf = cpf;
        this.cnh = cnh;
        this.cnhExpiration = cnhExpiration;
        this.email = email;
        this.ddd = ddd;
        this.phoneNumber = phoneNumber;
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
    public LocalDate getCnhExpiration() {
        return cnhExpiration;
    }
    public String getEmail() {
        return email;
    }
    public String getDdd() {
        return ddd;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public AddressEntity getAddress() {
        return address;
    }
    




}
