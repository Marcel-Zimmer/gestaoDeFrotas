package com.web2.trabalhoFinal.domain.model.trip;

public class StatusTrip {

    public enum Status {
        AGENDADO,
        EM_VIAGEM,
        EM_MANUTENCAO,
        EM_ABASTECIMENTO,
        FINALIZADO,
    }
    public Status fromString(String value) {
        return switch (value.toLowerCase()) {
            case "agendado"     -> Status.AGENDADO;
            case "em_viagem"         -> Status.EM_VIAGEM;
            case "em_manutencao"  -> Status.EM_MANUTENCAO;
            case "em_abastecimento"     -> Status.EM_ABASTECIMENTO;
            case "finalizado"     -> Status.FINALIZADO;
            default -> throw new IllegalArgumentException("Status inválido: " + value);
        };
    }
    
    private String status;

    
    public StatusTrip(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.fromString(this.status).name();
    }
}
