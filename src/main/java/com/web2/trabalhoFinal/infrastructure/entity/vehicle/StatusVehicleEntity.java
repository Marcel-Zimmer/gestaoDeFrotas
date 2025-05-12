package com.web2.trabalhoFinal.infrastructure.entity.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "status_vehicle")
public class StatusVehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_vehicle", nullable = false)
    private String statusVehicle;

    public StatusVehicleEntity(String status) {
        this.statusVehicle = status;
    }

    public StatusVehicleEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getStatusVehicle() {
        return statusVehicle;
    }

    public void setStatusVehicle(String statusVehicle) {
        switch (statusVehicle) {
            case "disponivel"     -> this.statusVehicle = "DISPONIVEL";
            case "em_uso"         -> this.statusVehicle ="EM_USO";
            case "em_manutenção"  -> this.statusVehicle ="EM_MANUTENCAO";
            default     -> this.statusVehicle ="DESATIVADO";
        };        
    }

}
