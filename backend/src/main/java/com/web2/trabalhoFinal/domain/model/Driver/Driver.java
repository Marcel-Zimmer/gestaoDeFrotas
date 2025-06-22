package com.web2.trabalhoFinal.domain.model.Driver;

import java.time.LocalDate;

import com.web2.trabalhoFinal.domain.model.User.User;

public class Driver extends User{
    private Cpf cpf;
    private Cnh cnh;
    private PhoneNumber phoneNumber;
    private Address address;
    private StatusDriver status;

    public Driver(String name, Cpf cpf, Cnh cnh, PhoneNumber phoneNumber, Address address,
        String email, String password, boolean isSuperUser, boolean isAtive, StatusDriver status) {
        super(name, email, password, isSuperUser, isAtive);
        this.cpf = cpf;
        this.cnh = cnh;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
    }   

    public String getValueCpf(){
        return cpf.getValue();
    }
    public Cpf getCpf(){
        return this.cpf;
    }
    public Cnh getCnh(){
        return this.cnh;
    }
    public PhoneNumber getPhoneNumber(){
        return this.phoneNumber;
    }
    public Address getAddress() {
        return address;
    }
    public String getValueCnh(){
        return cnh.getValue();
    }
    public LocalDate getValueExpirationDateCnh(){
        return cnh.getExpirationDate();
    }
    public String getValuePhoneNumberDriver(){
        return phoneNumber.getPhoneValue();
    }
    public String getValueDDDNumberDriver(){
        return phoneNumber.getDddValue();
    }

    public StatusDriver getStatus() {
        return status;
    }
    
}
