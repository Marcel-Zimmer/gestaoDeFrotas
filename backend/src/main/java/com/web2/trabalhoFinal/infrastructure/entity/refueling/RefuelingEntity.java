package com.web2.trabalhoFinal.infrastructure.entity.refueling;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
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

    @ManyToOne
    @JoinColumn(name = "id_driver", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity; 
    
    @Column(name = "date_refueling", nullable = false)
    private LocalDate refuelingDate;

    @Column(name = "price_refueling", nullable = false)
    private BigDecimal priceRefueling;    

    @Column(name = "current_mileage", nullable = false)
    private Double currentMileage;

    public RefuelingEntity() {}

    public RefuelingEntity(VehicleEntity vehicle, TypeRefuelingEntity typeRefueling, UserEntity userEntity,
            LocalDate refuelingDate, BigDecimal priceRefueling, Double currentMileage) {
        this.vehicle = vehicle;
        this.typeRefueling = typeRefueling;
        this.userEntity = userEntity;
        this.refuelingDate = refuelingDate;
        this.priceRefueling = priceRefueling;
        this.currentMileage = currentMileage;
    }

    public Long getId() {
        return id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public TypeRefuelingEntity getTypeRefueling() {
        return typeRefueling;
    }

    public UserEntity getUserEntity() {
        return userEntity;
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
