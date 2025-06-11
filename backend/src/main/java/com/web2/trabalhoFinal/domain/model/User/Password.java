package com.web2.trabalhoFinal.domain.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Password {
    @Column(insertable=false, updatable=false)
    private final String hashedValue;

    public Password(String plainPassword) {
        validate(plainPassword);
        this.hashedValue = hash(plainPassword);
    }
    
    public Password(){
        this.hashedValue = "";
    }

    private void validate(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("A senha não pode estar vazia.");
        }
        if (value.length() < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }
        if (!value.matches(".*[a-zA-Z].*") ||
            !value.matches(".*\\d.*") ||
            !value.matches(".*[^a-zA-Z0-9].*")) {
            throw new IllegalArgumentException("A senha deve conter letras, números e pelo menos um caractere especial.");
        }
    }

    private String hash(String plainPassword) {
        return new BCryptPasswordEncoder().encode(plainPassword);
    }

    public String getHashedValue() {
        return hashedValue;
    }

    public boolean matches(String rawPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, this.hashedValue);
    }
}