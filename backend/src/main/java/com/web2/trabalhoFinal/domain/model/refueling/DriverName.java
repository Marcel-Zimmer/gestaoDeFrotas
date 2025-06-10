package com.web2.trabalhoFinal.domain.model.refueling;

public class DriverName {
    private final String value;

    public DriverName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do motorista não pode ser vazio.");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("O nome do motorista não pode ter mais que 100 caracteres.");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }
}