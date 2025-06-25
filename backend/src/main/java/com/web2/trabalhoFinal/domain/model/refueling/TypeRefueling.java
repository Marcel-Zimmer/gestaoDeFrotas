package com.web2.trabalhoFinal.domain.model.refueling;


public class TypeRefueling {
    public enum Refueling {
        GASOLINA_COMUM,
        GASOLINA_ADITIVADA,
        ETANOL,
        DIESEL_COMUM,
        DIESEL_S10,
        GNV,
        ELETRICIDADE,
        BIODIESEL
    }
    public Refueling fromString(String value) {
            return switch (value.toLowerCase()) {
                case "gasolina_comum"     -> Refueling.GASOLINA_COMUM;
                case "gasolina_aditivada"         -> Refueling.GASOLINA_ADITIVADA;
                case "etanol"  -> Refueling.ETANOL;
                case "diesel_comum"     -> Refueling.DIESEL_COMUM;
                case "diesel_s10"     -> Refueling.DIESEL_S10;
                case "gnv"     -> Refueling.GNV;
                case "eletricidade"     -> Refueling.ELETRICIDADE;
                case "biodisel"     -> Refueling.BIODIESEL;
                default -> throw new IllegalArgumentException("Combustivel inválido: " + value);
        };
    } 
    private String refueling;
    
    public TypeRefueling(String refueling) {
        this.refueling = refueling;
    }

    public String getRefueling() {
        return this.fromString(this.refueling).name();
    }
}
