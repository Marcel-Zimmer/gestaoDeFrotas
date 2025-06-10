package com.web2.trabalhoFinal.domain.model.vehicle;


public class StatusVehicle {

    public enum Status {
        DISPONIVEL,
        EM_USO,
        EM_MANUTENCAO,
        DESATIVADO;
    }

    public Status fromString(String value) {
            return switch (value.toLowerCase()) {
                case "disponivel"     -> Status.DISPONIVEL;
                case "em uso"         -> Status.EM_USO;
                case "em manutenção"  -> Status.EM_MANUTENCAO;
                case "desativado"     -> Status.DESATIVADO;
                default -> throw new IllegalArgumentException("Status inválido: " + value);
        };
    }
    

    private String status;

    public StatusVehicle(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.fromString(this.status).name();
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
