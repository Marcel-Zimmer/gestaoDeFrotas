package com.web2.trabalhoFinal.infrastructure.entity.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "model_vehicle")
public class ModelVehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_vehicles", nullable = false)
    private String modelVehicle;

    public ModelVehicleEntity(String model) {
        this.modelVehicle = model;
    }

    public ModelVehicleEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getModelVehicle() {
        return modelVehicle;
    }

}
