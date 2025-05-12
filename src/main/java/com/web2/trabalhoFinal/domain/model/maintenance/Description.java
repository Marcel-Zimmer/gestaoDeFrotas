package com.web2.trabalhoFinal.domain.model.maintenance;

public class Description {
    private final String value;

    public Description(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia.");
        }
        if (value.length() < 3 || value.length() > 255) {
            throw new IllegalArgumentException("Descrição deve ter entre 3 e 255 caracteres.");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}