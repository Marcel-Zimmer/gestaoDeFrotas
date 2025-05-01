package com.web2.trabalhoFinal.domain.model;

public class Address {
    private String adress;

    public Address(){

    }
    public Address(String adress) {
        this.validateAdress(adress);
        this.adress = adress;
    }

    private void validateAdress(String adress) {
        if (adress == null || adress.trim().isEmpty()) {
            throw new IllegalArgumentException("O endereço não pode estar vazio.");
        }
        if (adress.length() < 5 || adress.length() > 100) {
            throw new IllegalArgumentException("O endereço deve ter entre 5 e 100 caracteres.");
        }
    }

    public String getValue() {
        return adress;
    }

}
 