package com.web2.trabalhoFinal.infrastructure.entity.trip;

import java.time.LocalDate;
import java.time.LocalTime;

import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
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
@Table(name = "trip")
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @ManyToOne
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id", nullable = false)
    private VehicleEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "id_driver", referencedColumnName = "id", nullable = false)
    private DriverEntity driver;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    private StatusTripEntity status;

    @Column(name = "date_trip", nullable = false)
    private LocalDate date;

    @Column(name = "time_trip", nullable = false)
    private LocalTime time;

    @Column(name= "justify", nullable = false)
    private String justify;

    public TripEntity(VehicleEntity vehicleId, DriverEntity driverId, StatusTripEntity status, LocalDate date,
            LocalTime time, String justify) {
        this.vehicle = vehicleId;
        this.driver = driverId;
        this.status = status;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.justify = justify;
    }


    public TripEntity() {
    }


    public Long getId() {
        return id;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public StatusTripEntity getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getJustify() {
        return justify;
    }

    

}
