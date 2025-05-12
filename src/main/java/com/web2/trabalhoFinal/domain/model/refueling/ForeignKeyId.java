package com.web2.trabalhoFinal.domain.model.refueling;

public class ForeignKeyId {
    private final Long value;

    public ForeignKeyId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
