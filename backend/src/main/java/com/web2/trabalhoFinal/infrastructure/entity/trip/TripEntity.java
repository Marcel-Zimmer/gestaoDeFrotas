package com.web2.trabalhoFinal.infrastructure.entity.trip;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "id_destiny", referencedColumnName = "id", nullable = false)
    private AddressDestinyEntity destiny;    

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id", nullable = false)
    private StatusTripEntity status;

    @Column(name = "date_trip", nullable = false)
    private LocalDateTime date;

    @Column(name= "justify", nullable = false)
    private String justify;

    public TripEntity(VehicleEntity vehicleId, DriverEntity driverId, StatusTripEntity status, LocalDateTime date, String justify, AddressDestinyEntity destiny) {
        this.vehicle = vehicleId;
        this.driver = driverId;
        this.status = status;
        this.date = date;
        this.justify = justify;
        this.destiny = destiny;
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


    public AddressDestinyEntity getDestiny() {
        return destiny;
    }


    public StatusTripEntity getStatus() {
        return status;
    }


    public LocalDateTime getDate() {
        return date;
    }


    public String getJustify() {
        return justify;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }


    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }


    public void setDestiny(AddressDestinyEntity destiny) {
        this.destiny = destiny;
    }


    public void setStatus(StatusTripEntity status) {
        this.status = status;
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    public void setJustify(String justify) {
        this.justify = justify;
    }



    

}
