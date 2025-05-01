package com.web2.trabalhoFinal.domain.model;

public class Email {
    private String email;

    public Email(String email) {
        this.validateEmail(email);
        this.email = email;
    }

    public Email(){

    }
    
    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail não pode estar vazio.");
        }

        // Regex simples para validar formato de e-mail
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }
    }

    public String getValue() {
        return email;
    }


}
