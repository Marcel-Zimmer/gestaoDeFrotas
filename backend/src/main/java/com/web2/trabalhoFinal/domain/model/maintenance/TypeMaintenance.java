package com.web2.trabalhoFinal.domain.model.maintenance;

public class TypeMaintenance {

    public enum Maintenance {
        PREVENTIVA,
        CORRETIVA
    }
    public Maintenance fromString(String value) {
            return switch (value.toLowerCase()) {
                case "preventiva"     -> Maintenance.PREVENTIVA;
                case "corretiva"         -> Maintenance.CORRETIVA;
                default -> throw new IllegalArgumentException("Tipo de manutenção inválido: " + value);
        };
    }     
    private String typeMaintenance;
    
    public TypeMaintenance(String typeMaintenance) {
        this.typeMaintenance = typeMaintenance;
    }

    public String getTypeMaintenance() {
        return this.fromString(this.typeMaintenance).name();
    }
}
