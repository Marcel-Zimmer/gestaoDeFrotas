package com.web2.trabalhoFinal.domain.model.vehicle;

public class LicencePlate {

    private String licence;

    public LicencePlate(String licence) {
        this.validate(licence);
        this.licence = licence.toUpperCase();
    }

    private void validate(String licence) {
        if (licence == null || !licence.matches("^[A-Z0-9]{7}$")) {
            throw new IllegalArgumentException("Placa inválida");
        }
    }

    public String getLicence() {
        return licence;
    }

}
