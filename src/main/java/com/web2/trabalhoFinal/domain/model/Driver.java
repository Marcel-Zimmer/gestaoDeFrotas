package com.web2.trabalhoFinal.domain.model;

import java.time.LocalDate;

public class Driver {
    private Name name;
    private Cpf cpf;
    private Cnh cnh;
    private PhoneNumber phoneNumber;
    private Address address;
    private Email email;
    private Password password;

    public Driver(String name, String cpf, String cnh, LocalDate expirationDate, String phoneNumber, String email, String password,String zipCode,String street,
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
    
    public String getNameDriver(){
        return name.getValue();
    }

    public String getCpfDriver(){
        return cpf.getValue();
    }

    public String getCnhDriver(){
        return cnh.getValue();
    }

    public LocalDate getExpirationDateCnh(){
        return cnh.getExpirationDate();
    }

    public String getPhoneNumberDriver(){
        return phoneNumber.getValue();
    }

    public String getEmailDriver(){
        return email.getValue();
    }
    public String getPasswordDriver(){
        return password.getHashedValue();
    }
    public String getZipCode() {
        return address.getZipCode();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getComplement() {
        return address.getComplement();
    }

    public String getUnit() {
        return address.getUnit();
    }

    public String getNeighborhood() {
        return address.getNeighborhood();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getStateAbbreviation() {
        return address.getStateAbbreviation();
    }

    public String getState() {
        return address.getState();
    }

    public String getRegion() {
        return address.getRegion();
    }

    public String getIbgeCode() {
        return address.getIbgeCode();
    }

    public String getGiaCode() {
        return address.getGiaCode();
    }

    public String getDdd() {
        return address.getDdd();
    }

    public String getSiafiCode() {
        return address.getSiafiCode();
    }

    public String getNumberAddress() {
        return address.getNumberAddress();
    }
}
