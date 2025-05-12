package com.web2.trabalhoFinal.domain.model.vehicle;

public class TypeVehicle {

    private String type;

    public TypeVehicle(String type) {
        this.validate(type);
        this.type = type.toUpperCase();
    }

    private void validate(String type) {
        if (type == null || !type.matches("^[A-Za-z0-9 .\\-/]{2,}$")) {
            throw new IllegalArgumentException("tipo inválido");
        }
    }

    public String getType() {
        return type;
    }

}
