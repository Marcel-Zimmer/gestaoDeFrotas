package com.web2.trabalhoFinal.domain.model.trip;

public class StatusTrip {

    public enum Status {
        CANCELADO,
        PENDENTE,
        EM_TRANSITO,
        FINALIZADO;

        public static Status fromString(String value) {
            return switch (value.toLowerCase()) {
                case "cancelado"     -> CANCELADO;
                case "pendente"         -> PENDENTE;
                case "em transito"  -> EM_TRANSITO;
                case "finalizado"     -> FINALIZADO;
                default -> throw new IllegalArgumentException("Status inválido: " + value);
            };
        }
    }
    private String status;

    public StatusTrip(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
