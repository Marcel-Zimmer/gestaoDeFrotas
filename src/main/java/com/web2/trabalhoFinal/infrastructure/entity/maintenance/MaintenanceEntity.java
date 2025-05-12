package com.web2.trabalhoFinal.infrastructure.entity.maintenance;

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
@Table(name = "maintenance")
public class MaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id", nullable = false)
    private VehicleEntity vehicle;  

    @ManyToOne
    @JoinColumn(name = "id_type_maintenance", referencedColumnName = "id", nullable = false)
    private TypeMaintenanceEntity typeMaintenanceEntity;    

    @Column(name = "date_maintenance", nullable = false)
    private LocalDate dateMaintenance;
    
    @Column(name = "description_maintenance", nullable = false)
    private String descriptionMaintenance;

    @Column(name = "price_maintenance", nullable = false)
    private BigDecimal priceMaintenance;    

    @Column(name = "current_mileage", nullable = false)
    private Double currentMileage;

    public MaintenanceEntity(){}
    
    public MaintenanceEntity(VehicleEntity vehicle, TypeMaintenanceEntity typeMaintenanceEntity,
            LocalDate dateMaintenance, String descriptionMaintenance, BigDecimal priceMaintenance,
            Double currentMileage) {
        this.vehicle = vehicle;
        this.typeMaintenanceEntity = typeMaintenanceEntity;
        this.dateMaintenance = dateMaintenance;
        this.descriptionMaintenance = descriptionMaintenance;
        this.priceMaintenance = priceMaintenance;
        this.currentMileage = currentMileage;
    }

    public Long getId() {
        return id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public TypeMaintenanceEntity getTypeMaintenanceEntity() {
        return typeMaintenanceEntity;
    }

    public LocalDate getDateMaintenance() {
        return dateMaintenance;
    }

    public String getDescriptionMaintenance() {
        return descriptionMaintenance;
    }

    public BigDecimal getPriceMaintenance() {
        return priceMaintenance;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }
    
    
}
