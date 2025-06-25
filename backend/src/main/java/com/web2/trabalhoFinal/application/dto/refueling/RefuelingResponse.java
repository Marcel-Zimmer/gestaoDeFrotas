package com.web2.trabalhoFinal.application.dto.refueling;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;

public class RefuelingResponse {
    private Long id;
    private VehicleEntity vehicle;
    private String typeRefueling;
    private String nameDriver; 
    private LocalDate refuelingDate;
    private BigDecimal priceRefueling;
    private Double currentMileage;
    
    public RefuelingResponse(Long id, VehicleEntity vehicle, String typeRefueling, String nameDriver, LocalDate refuelingDate,
            BigDecimal priceRefueling, Double currentMileage) {
        this.id = id;
        this.vehicle = vehicle;
        this.typeRefueling = typeRefueling;
        this.nameDriver = nameDriver;
        this.refuelingDate = refuelingDate;
        this.priceRefueling = priceRefueling;
        this.currentMileage = currentMileage;
    }

    public RefuelingResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public String getTypeRefueling() {
        return typeRefueling;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public LocalDate getRefuelingDate() {
        return refuelingDate;
    }

    public BigDecimal getPriceRefueling() {
        return priceRefueling;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }
    

}
