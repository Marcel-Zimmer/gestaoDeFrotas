package com.web2.trabalhoFinal.domain.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email {
    @Column(insertable=false, updatable=false)
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

        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Formato de e-mail inválido.");
        }
    }

    public String getValue() {
        return email;
    }


}
