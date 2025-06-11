package com.web2.trabalhoFinal.domain.model.Vehicle;

public class CurrentMileage {

    private Double currentMileage;

    public CurrentMileage(String mileage) {
        this.currentMileage = validateAndParse(mileage);
    }

    private Double validateAndParse(String mileage) {
        try {
            Double value = Double.parseDouble(mileage);
            if (value < 0) {
                throw new IllegalArgumentException("Quilometragem não pode ser negativa.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Quilometragem inválida.");
        }
    }

    public Double getValue() {
        return currentMileage;
    }
}