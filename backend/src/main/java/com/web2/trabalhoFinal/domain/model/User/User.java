package com.web2.trabalhoFinal.domain.model.User;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class User {
    private Name name;
    private Email email;
    private Password password;
    private boolean isSuperUser;
    private boolean isAtive;
    private String passwordRaw;
    private Address address;
    private Cpf cpf;
    private PhoneNumber phoneNumber;

    public User(){}
    
    public User(Name name, Email email, Password password, boolean isSuperUser, boolean isAtive, PhoneNumber phoneNumber, Address address, Cpf cpf) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.isSuperUser = isSuperUser;
        this.isAtive = isAtive;
        this.address = address;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

    public User(Name name, Email email, boolean isSuperUser, boolean isAtive, PhoneNumber phoneNumber, Address address, Cpf cpf) {
        this.name = name;
        this.email = email;
        this.isSuperUser = isSuperUser;
        this.isAtive = isAtive;
        this.address = address;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
    }

    public User(String email, String password){
        this.email= new Email(email);
        this.passwordRaw = password;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public boolean isAtive() {
        return isAtive;
    }

    public Address getAddress() {
        return address;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public String getPasswordRaw() {
        return passwordRaw;
    }

    
}