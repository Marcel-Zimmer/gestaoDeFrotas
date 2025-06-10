package com.web2.trabalhoFinal.infrastructure.entity.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "type_vehicle")
public class TypeVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_vehicle", nullable = false)
    private String typeVehicle;

    public TypeVehicleEntity(String type) {
        this.typeVehicle = type;
    }

    public TypeVehicleEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

}
