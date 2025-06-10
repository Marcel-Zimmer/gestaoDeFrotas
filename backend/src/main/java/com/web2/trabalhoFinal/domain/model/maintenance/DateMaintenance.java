package com.web2.trabalhoFinal.domain.model.maintenance;

import java.time.LocalDate;

public class DateMaintenance {
    private final LocalDate date;

    public DateMaintenance(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("A data de manutenção não pode ser nula.");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de manutenção não pode estar no futuro.");
        }
        this.date = date;
    }

    public LocalDate getValue() {
        return date;
    }
}