package com.web2.trabalhoFinal.infrastructure.entity.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "year_vehicle")
public class YearVehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year_vehicle", nullable = false)
    private String yearVehicle;

    public YearVehicleEntity(String year) {
        this.yearVehicle = year;
    }

    public YearVehicleEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getYearVehicle() {
        return yearVehicle;
    }

}
