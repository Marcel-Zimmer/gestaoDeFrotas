package com.web2.trabalhoFinal.infrastructure.entity.vehicle;
import com.web2.trabalhoFinal.domain.model.Vehicle.Vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table(name = "vehicles")
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "licence_plate", nullable = false,unique = true)
    private String licencePlate;

    @Column(name = "current_mileage", nullable = false)
    private Double currentMileage;
    
    @ManyToOne
    @JoinColumn(name = "id_model_vehicle", referencedColumnName = "id", nullable = false)
    private ModelVehicleEntity modelVehicleEntity;

    @ManyToOne
    @JoinColumn(name = "id_status_vehicle", referencedColumnName = "id", nullable = false)
    private StatusVehicleEntity statusVehicleEntity;

    @ManyToOne
    @JoinColumn(name = "id_type_vehicle", referencedColumnName = "id", nullable = false)
    private TypeVehicleEntity typeVehicleEntity;

    @ManyToOne
    @JoinColumn(name = "id_year_vehicle", referencedColumnName = "id", nullable = false)
    private YearVehicleEntity yearVehicleEntity;

    public VehicleEntity(){}
    
    public VehicleEntity(Long id) {
        this.id = id;
    }

    public VehicleEntity(String licencePlate, Double currentMileage, ModelVehicleEntity modelVehicleEntity,
            StatusVehicleEntity statusVehicleEntity, TypeVehicleEntity typeVehicleEntity,
            YearVehicleEntity yearVehicleEntity) {
        this.licencePlate = licencePlate;
        this.currentMileage = currentMileage;
        this.modelVehicleEntity = modelVehicleEntity;
        this.statusVehicleEntity = statusVehicleEntity;
        this.typeVehicleEntity = typeVehicleEntity;
        this.yearVehicleEntity = yearVehicleEntity;
    }

    public Long getId() {
        return id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public Double getCurrentMileage() {
        return currentMileage;
    }

    public ModelVehicleEntity getModelVehicleEntity() {
        return modelVehicleEntity;
    }

    public StatusVehicleEntity getStatusVehicleEntity() {
        return statusVehicleEntity;
    }

    public TypeVehicleEntity getTypeVehicleEntity() {
        return typeVehicleEntity;
    }

    public YearVehicleEntity getYearVehicleEntity() {
        return yearVehicleEntity;
    }

    public void setStatusVehicleEntity(StatusVehicleEntity statusVehicleEntity) {
        this.statusVehicleEntity = statusVehicleEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setCurrentMileage(Double currentMileage) {
        this.currentMileage = currentMileage;
    }

    public void setModelVehicleEntity(ModelVehicleEntity modelVehicleEntity) {
        this.modelVehicleEntity = modelVehicleEntity;
    }

    public void setTypeVehicleEntity(TypeVehicleEntity typeVehicleEntity) {
        this.typeVehicleEntity = typeVehicleEntity;
    }

    public void setYearVehicleEntity(YearVehicleEntity yearVehicleEntity) {
        this.yearVehicleEntity = yearVehicleEntity;
    }

    
    
}
