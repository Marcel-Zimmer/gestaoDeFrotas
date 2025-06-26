package com.web2.trabalhoFinal.application.dto.maintenance;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;



public class MaintenanceResponse {
    private Long id;
    private VehicleEntity vehicle;
    private LocalDate date;
    private BigDecimal price;
    private Double currentMileage;
    private String description;
    private String type;

    public MaintenanceResponse(Long id, VehicleEntity vehicle, LocalDate date, BigDecimal price, Double currentMileage,
            String description, String type) {
        this.id = id;
        this.vehicle = vehicle;
        this.date = date;
        this.price = price;
        this.currentMileage = currentMileage;
        this.description = description;
        this.type = type;
    }

    public MaintenanceResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }
        
}