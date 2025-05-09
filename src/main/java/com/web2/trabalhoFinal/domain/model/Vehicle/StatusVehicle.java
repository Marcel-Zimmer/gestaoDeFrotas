package com.web2.trabalhoFinal.domain.model.Vehicle;


public class StatusVehicle {

    public enum Status {
        DISPONIVEL,
        EM_USO,
        EM_MANUTENCAO,
        DESATIVADO;

        public static Status fromString(String value) {
            return switch (value.toLowerCase()) {
                case "disponivel"     -> DISPONIVEL;
                case "em uso"         -> EM_USO;
                case "em manutenção"  -> EM_MANUTENCAO;
                case "desativado"     -> DESATIVADO;
                default -> throw new IllegalArgumentException("Status inválido: " + value);
            };
        }
    }

    private String status;

    public StatusVehicle(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
