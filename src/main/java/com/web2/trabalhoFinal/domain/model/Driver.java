package com.web2.trabalhoFinal.domain.model;

public class Driver {
    private Name name;
    private Cpf cpf;
    private Cnh cnh;
    private PhoneNumber phoneNumber;
    private Address address;
    private Email email;
    private Password password;

    public Driver(String name, String cpf, String cnh, String phoneNumber, String adress, String email, String password){
        this.name = new Name(name);
        this.cpf = new Cpf(cpf);
        this.cnh = new Cnh(cnh);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.address = new Address(adress);
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
    public String getPhoneNumberDriver(){
        return phoneNumber.getValue();
    }
    public String getAdressDriver(){
        return address.getValue();
    }
    public String getEmailDriver(){
        return email.getValue();
    }
    public String getPasswordDriver(){
        return password.getHashedValue();
    }
}
