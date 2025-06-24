package com.web2.trabalhoFinal.application.dto.administrador;

import com.web2.trabalhoFinal.infrastructure.entity.user.AddressEntity;


public class AdministradorResponse {
    private Long administradorId;
    private String nameAdministrador;
    private String cpf;
    private String email;
    private String phoneNumber;
    private AddressEntity address;
    
    public AdministradorResponse(Long administradorId, String nameAdministrador, String cpf, String email,
            String phoneNumber, AddressEntity address) {
        this.administradorId = administradorId;
        this.nameAdministrador = nameAdministrador;
        this.cpf = cpf;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public AdministradorResponse(Long administradorId) {
        this.administradorId = administradorId;
    }

    public Long getAdministradorId() {
        return administradorId;
    }

    public String getNameAdministrador() {
        return nameAdministrador;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddressEntity getAddress() {
        return address;
    }

    
}
