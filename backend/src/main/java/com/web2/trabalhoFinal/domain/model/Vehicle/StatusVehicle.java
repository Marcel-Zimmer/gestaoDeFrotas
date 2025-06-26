package com.web2.trabalhoFinal.domain.model.Vehicle;


public class StatusVehicle {

    public enum Status {
        DISPONIVEL,
        EM_VIAGEM,
        EM_MANUTENCAO,
    }

    public Status fromString(String value) {
            return switch (value.toLowerCase()) {
                case "disponivel"     -> Status.DISPONIVEL;
                case "em_viagem"         -> Status.EM_VIAGEM;
                case "em_manutencao"  -> Status.EM_MANUTENCAO;
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
