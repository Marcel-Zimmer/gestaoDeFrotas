package com.web2.trabalhoFinal.domain.model.refueling;

import java.math.BigDecimal;

public class Price {
    private final BigDecimal value;

    public Price(BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O preço do abastecimento deve ser maior que zero.");
        }
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}