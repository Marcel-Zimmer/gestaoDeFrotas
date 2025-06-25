package com.web2.trabalhoFinal.infrastructure.entity.refueling;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "refueling")
public class RefuelingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id", nullable = false)
    private VehicleEntity vehicle;    

    @ManyToOne
    @JoinColumn(name = "id_type_refueling", referencedColumnName = "id", nullable = false)
    private TypeRefuelingEntity typeRefueling;

    @Column(name = "name_driver", nullable = false)
    private String nameDriver; 
    
    @Column(name = "date_refueling", nullable = false)
    private LocalDate refuelingDate;

    @Column(name = "price_refueling", nullable = false)
    private BigDecimal priceRefueling;    

    @Column(name = "current_mileage", nullable = false)
    private Double currentMileage;

    public RefuelingEntity() {}

    public RefuelingEntity(VehicleEntity vehicle, TypeRefuelingEntity typeRefueling, String nameDriver,
            LocalDate refuelingDate, BigDecimal priceRefueling, Double currentMileage) {
        this.vehicle = vehicle;
        this.typeRefueling = typeRefueling;
        this.nameDriver = nameDriver;
        this.refuelingDate = refuelingDate;
        this.priceRefueling = priceRefueling;
        this.currentMileage = currentMileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public TypeRefuelingEntity getTypeRefueling() {
        return typeRefueling;
    }

    public void setTypeRefueling(TypeRefuelingEntity typeRefueling) {
        this.typeRefueling = typeRefueling;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver = nameDriver;
    }

    public LocalDate getRefuelingDate() {
        return refuelingDate;
    }

    public void setRefuelingDate(LocalDate refuelingDate) {
        this.refuelingDate = refuelingDate;
    }

    public BigDecimal getPriceRefueling() {
        return priceRefueling;
    }

    public void setPriceRefueling(BigDecimal priceRefueling) {
        this.priceRefueling = priceRefueling;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Double currentMileage) {
        this.currentMileage = currentMileage;
    }


         
}
