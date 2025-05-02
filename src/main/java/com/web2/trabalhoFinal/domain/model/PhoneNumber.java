package com.web2.trabalhoFinal.domain.model;

public class PhoneNumber {

    private String phoneNumber;
    private String dddNumber;

    public PhoneNumber(String phoneNumber, String dddNumber) {
        validate(phoneNumber);
        validateDdd(dddNumber);
        this.phoneNumber = phoneNumber;
        this.dddNumber = dddNumber;
    }

    public PhoneNumber(){
        this.phoneNumber = "";
        
    }

    private void validate(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("O número de telefone não pode estar vazio.");
        }

        String phoneRegex = "^(\\(?\\d{2}\\)?\\s?)?(9\\d{4}|[2-8]\\d{3})-?\\d{4}$";

        if (!phoneNumber.matches(phoneRegex)) {
            throw new IllegalArgumentException("Número de telefone inválido.");
        }
    }
    private void validateDdd(String ddd) {
        if (ddd == null || ddd.trim().isEmpty()) {
            throw new IllegalArgumentException("O DDD não pode estar vazio.");
        }
    
        String dddRegex = "^\\d{2}$";
    
        if (!ddd.matches(dddRegex)) {
            throw new IllegalArgumentException("DDD inválido. Deve conter exatamente dois dígitos.");
        }
    }    

    public String getPhoneValue() {
        return phoneNumber;
    }

    public String getDddValue() {
        return dddNumber;
    }
}