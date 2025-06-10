package com.web2.trabalhoFinal.domain.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Name {

    @Column(insertable=false, updatable=false)
    private String name;

    public Name(String name) {
        this.validateName(name);
        this.name = name;
    }

    public Name(){

    }
    
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }
        if (name.length() < 2 || name.length() > 20) {
            throw new IllegalArgumentException("O endereço deve ter entre 2 e 20 caracteres.");
        }
    }

    public String getValue() {
        return name;
    }
}
