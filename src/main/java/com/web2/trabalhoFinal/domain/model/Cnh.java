package com.web2.trabalhoFinal.domain.model;

//import java.util.Date;

public class Cnh {

    private String cnh;
    //private Date expirationDate;

    public Cnh(){

    }
    
    public Cnh(String cnh) {
        this.validateCnh(cnh);
        this.cnh = cnh;
    }

    private void validateCnh(String cnh) {
        if (cnh == null || cnh.trim().isEmpty()) {
            throw new IllegalArgumentException("a cnh não pode estar vazia");
        }
        if (!cnh.matches("\\d{11}")) {
            throw new IllegalArgumentException("A CNH deve conter exatamente 11 dígitos numéricos.");
        }
    }

    public String getValue() {
        return cnh;
    }

}
