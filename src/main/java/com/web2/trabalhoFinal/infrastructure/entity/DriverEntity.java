package com.web2.trabalhoFinal.infrastructure.entity;


import java.time.LocalDate;

import com.web2.trabalhoFinal.domain.model.Address;
import com.web2.trabalhoFinal.domain.model.Cnh;
import com.web2.trabalhoFinal.domain.model.Cpf;
import com.web2.trabalhoFinal.domain.model.Email;
import com.web2.trabalhoFinal.domain.model.Name;
import com.web2.trabalhoFinal.domain.model.Password;
import com.web2.trabalhoFinal.domain.model.PhoneNumber;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "driver")
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Cpf cpf;

    @Embedded
    private Cnh cnh;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Address address;

    @Embedded
    private Email email;

    @Embedded
    private Password password;


    public DriverEntity() {
    }

    public DriverEntity(String name, String cpf, String cnh, LocalDate expirationDate, String phoneNumber, String email, String password,String zipCode,String street,
    String complement,String unit,String neighborhood,String city,String stateAbbreviation,String state ,String region,String ibgeCode,
    String giaCode,String ddd,String siafiCode,String numberAdress){
        this.name = new Name(name);
        this.cpf = new Cpf(cpf);
        this.cnh = new Cnh(cnh,expirationDate);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = new Address(zipCode,street,complement,unit,neighborhood,city,stateAbbreviation,state,region,ibgeCode,giaCode,ddd,siafiCode,numberAdress);
        this.email = new Email(email);
        this.password = new Password(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Name getName() {
        return name;
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

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}