
package com.web2.trabalhoFinal.domain.model.Driver;


public class StatusDriver {
    public enum Status {
        DISPONIVEL,
        EM_VIAGEM,
        EM_MANUTENCAO,
        AFASTADO,
        DESATIVADO;
    }

    public Status fromString(String value) {
            return switch (value.toLowerCase()) {
                case "disponivel"     -> Status.DISPONIVEL;
                case "em viagem"         -> Status.EM_VIAGEM;
                case "em manutenção"  -> Status.EM_MANUTENCAO;
                case "afastado"  -> Status.AFASTADO;
                case "desativado"     -> Status.DESATIVADO;
                default -> throw new IllegalArgumentException("Status inválido: " + value);
        };
    }
    

    private String status;

    public StatusDriver(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.fromString(this.status).name();
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
