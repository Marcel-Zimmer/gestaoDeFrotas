package com.web2.trabalhoFinal.infrastructure.entity;

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

    public DriverEntity(String name, String cpf, String cnh, String phoneNumber, String adress, String email, String password) {
        this.name = new Name(name);
        this.cpf = new Cpf(cpf);
        this.cnh = new Cnh(cnh);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = new Address(adress);
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

    public void setName(Name name) {
        this.name = name;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public void setCpf(Cpf cpf) {
        this.cpf = cpf;
    }

    public Cnh getCnh() {
        return cnh;
    }

    public void setCnh(Cnh cnh) {
        this.cnh = cnh;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}