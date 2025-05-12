package com.web2.trabalhoFinal.domain.model.refueling;

import java.time.LocalDate;

public class DateRefueling {
    private final LocalDate date;

    public DateRefueling(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("A data de abastecimento não pode ser nula.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de abastecimento não pode estar no futuro.");
        }
        this.date = date;
    }

    public LocalDate getValue() {
        return date;
    }
}