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

    @Column(name = "start_mileage")
    private Double startMileage;

    @Column(name = "end_mileage")
    private Double endMileage;
    
    @Column(name = "actual_departure_time")
    private LocalDateTime actualDepartureTime;

    @Column(name = "actual_arrival_time")
    private LocalDateTime actualArrivalTime;

    // columnDefinition = "TEXT" sugere ao banco um tipo de coluna para textos longos.
    @Column(name = "start_observations", columnDefinition = "TEXT")
    private String startObservations;

    @Column(name = "end_observations", columnDefinition = "TEXT")
    private String endObservations;

    public TripEntity(VehicleEntity vehicleId, DriverEntity driverId, StatusTripEntity status, LocalDateTime date, String justify, AddressDestinyEntity destiny) {
        this.vehicle = vehicleId;
        this.driver = driverId;
        this.status = status;
        this.date = date;
        this.justify = justify;
        this.destiny = destiny;
    }

    public TripEntity(){}
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

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public AddressDestinyEntity getDestiny() {
        return destiny;
    }

    public void setDestiny(AddressDestinyEntity destiny) {
        this.destiny = destiny;
    }

    public StatusTripEntity getStatus() {
        return status;
    }

    public void setStatus(StatusTripEntity status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getJustify() {
        return justify;
    }

    public void setJustify(String justify) {
        this.justify = justify;
    }

    public Double getStartMileage() {
        return startMileage;
    }

    public void setStartMileage(Double startMileage) {
        this.startMileage = startMileage;
    }

    public Double getEndMileage() {
        return endMileage;
    }

    public void setEndMileage(Double endMileage) {
        this.endMileage = endMileage;
    }

    public LocalDateTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public void setActualDepartureTime(LocalDateTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public LocalDateTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public String getStartObservations() {
        return startObservations;
    }

    public void setStartObservations(String startObservations) {
        this.startObservations = startObservations;
    }

    public String getEndObservations() {
        return endObservations;
    }

    public void setEndObservations(String endObservations) {
        this.endObservations = endObservations;
    }






    

}
