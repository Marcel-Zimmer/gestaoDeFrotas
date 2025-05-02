package com.web2.trabalhoFinal.infrastructure.entity;

import com.web2.trabalhoFinal.domain.model.Address;
import com.web2.trabalhoFinal.domain.model.Cnh;
import com.web2.trabalhoFinal.domain.model.Cpf;
import com.web2.trabalhoFinal.domain.model.PhoneNumber;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "driver")
public class DriverEntity{

    private String name;
    private String email;
    private String password;
    private boolean isSuperUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Cpf cpf;

    @Embedded
    private Cnh cnh;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Address address;


    public DriverEntity() {
    }

    public DriverEntity(String name, Cpf cpf, Cnh cnh, PhoneNumber phoneNumber, Address address,
    String email, String password, boolean isSuperUser) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSuperUser = isSuperUser;
        this.cpf = cpf;
        this.cnh = cnh;
        this.phoneNumber = phoneNumber;
        this.address = address;
         this.address = address;
    } 
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Cnh getCnh() {
        return cnh;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

}