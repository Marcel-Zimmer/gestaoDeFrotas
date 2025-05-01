package com.web2.trabalhoFinal.domain.model;

public class PhoneNumber {
    private final String phoneNumber;

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
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

    public String getValue() {
        return phoneNumber;
    }
}