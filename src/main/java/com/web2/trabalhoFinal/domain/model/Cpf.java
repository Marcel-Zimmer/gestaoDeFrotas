package com.web2.trabalhoFinal.domain.model;

public class Cpf {
    private String cpf;

    public Cpf(String cpf) {
        this.validateCph(cpf);
        this.cpf = cpf;
    }

    public Cpf(){
        
    }

    private void validateCph(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("o cpf não pode estar vazia");
        }
        if (!cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("O CPF deve conter exatamente 11 dígitos numéricos.");
        }
    }
    public String getValue() {
        return cpf;
    } 

}
