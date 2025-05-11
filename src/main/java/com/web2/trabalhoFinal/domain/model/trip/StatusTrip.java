package com.web2.trabalhoFinal.domain.model.trip;

public class StatusTrip {

    public enum Status {
        CANCELADO,
        PENDENTE,
        EM_TRANSITO,
        FINALIZADO;
    }
    public Status fromString(String value) {
        return switch (value.toLowerCase()) {
            case "cancelado"     -> Status.CANCELADO;
            case "pendente"         -> Status.PENDENTE;
            case "em transito"  -> Status.EM_TRANSITO;
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
