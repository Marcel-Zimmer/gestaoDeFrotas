package com.web2.trabalhoFinal.domain.model.driver;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Cnh {
    @Column(name = "cnh")
    private String cnh;
    @Column(name = "expirationDate")
    private LocalDate expirationDate;

    public Cnh() {
    }

    public Cnh(String cnh, LocalDate expirationDate) {
        this.validateCnh(cnh);
        this.validateExpirationDate(expirationDate);
        this.cnh = cnh;
        this.expirationDate = expirationDate;
    }

    private void validateCnh(String cnh) {
        if (cnh == null || cnh.trim().isEmpty()) {
            throw new IllegalArgumentException("A CNH não pode estar vazia");
        }
        if (!cnh.matches("\\d{11}")) {
            throw new IllegalArgumentException("A CNH deve conter exatamente 11 dígitos numéricos.");
        }
    }

    private void validateExpirationDate(LocalDate expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("A data de expiração não pode ser nula.");
        }
        LocalDate currentDate = LocalDate.now();
        if (expirationDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("A data de expiração não pode ser no passado.");
        }
    }

    public String getValue() {
        return cnh;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
