package com.web2.trabalhoFinal.domain.model.trip;

import java.time.LocalDateTime;


public class Trip {
    
    private ForeignKeyId idVehicle ;
    private ForeignKeyId idDriver;
    private LocalDateTime date;
    private JustifyTrip justify;
    private StatusTrip status;
    private AddressTrip destiny;
    private LocalDateTime actualDepartureTime;
    private LocalDateTime actualArrivalTime;
    private Double startMileage;
    private Double endMileage;
    private String startObservations;
    private String endObservations;    

    public Trip(){}
    
    public Trip(Long idVehicle, Long idDriver, LocalDateTime date, String justify, String status, AddressTrip destiny) {
        this.idVehicle = new ForeignKeyId(idVehicle);
        this.idDriver = new ForeignKeyId(idDriver);
        this.date = date;
        this.justify = new JustifyTrip(justify);
        this.status = new StatusTrip(status);
        this.destiny = destiny;
    }

    public ForeignKeyId getIdVehicle() {
        return idVehicle;
    }

    public ForeignKeyId getIdDriver() {
        return idDriver;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public JustifyTrip getJustify() {
        return justify;
    }

    public StatusTrip getStatus() {
        return status;
    }

    public AddressTrip getAddress() {
        return destiny;
    }

    public LocalDateTime getActualDepartureTime() {
        return actualDepartureTime;
    }

    public LocalDateTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public Double getStartMileage() {
        return startMileage;
    }

    public Double getEndMileage() {
        return endMileage;
    }

    public String getStartObservations() {
        return startObservations;
    }

    public String getEndObservations() {
        return endObservations;
    }

    public void setIdVehicle(ForeignKeyId idVehicle) {
        this.idVehicle = idVehicle;
    }

    public void setIdDriver(ForeignKeyId idDriver) {
        this.idDriver = idDriver;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setJustify(JustifyTrip justify) {
        this.justify = justify;
    }

    public void setStatus(StatusTrip status) {
        this.status = status;
    }

    public void setDestiny(AddressTrip destiny) {
        this.destiny = destiny;
    }

    public void setActualDepartureTime(LocalDateTime actualDepartureTime) {
        this.actualDepartureTime = actualDepartureTime;
    }

    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public void setStartMileage(Double startMileage) {
        this.startMileage = startMileage;
    }

    public void setEndMileage(Double endMileage) {
        this.endMileage = endMileage;
    }

    public void setStartObservations(String startObservations) {
        this.startObservations = startObservations;
    }

    public void setEndObservations(String endObservations) {
        this.endObservations = endObservations;
    }


    
}
