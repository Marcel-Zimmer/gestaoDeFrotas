package com.web2.trabalhoFinal.domain.model.Vehicle;

public class ModelVehicle {

    private String model;

    public ModelVehicle(String model) {
        this.validate(model);
        this.model = model.toUpperCase();
    }

    private void validate(String model) {
        if (model == null || !model.matches("^[A-Za-z0-9 .\\-/]{2,}$")) {
            throw new IllegalArgumentException("modelo inválido");
        }
    }

    public String getModel() {
        return model;
    }

    
}
