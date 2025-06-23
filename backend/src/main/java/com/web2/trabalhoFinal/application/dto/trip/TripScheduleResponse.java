package com.web2.trabalhoFinal.application.dto.trip;

import java.time.LocalDateTime;

import com.web2.trabalhoFinal.infrastructure.entity.trip.AddressDestinyEntity;




public class TripScheduleResponse {
    private Long tripId;
    private String justify;
    private LocalDateTime date;
    private String statusTrip;
    private Long idDriver;
    private Long idVehicle;
    private String nameDriver;
    private String licencePlate;
    private String modelVehicle;
    private String typeVehicle;
    private AddressDestinyEntity address;

    public TripScheduleResponse(AddressDestinyEntity address,LocalDateTime date, Long idDriver, Long idVehicle, String justify, String licencePlate, String modelVehicle, String nameDriver, String statusTrip, Long tripId, String typeVehicle) {
        this.address = address;
        this.date = date;
        this.idDriver = idDriver;
        this.idVehicle = idVehicle;
        this.justify = justify;
        this.licencePlate = licencePlate;
        this.modelVehicle = modelVehicle;
        this.nameDriver = nameDriver;
        this.statusTrip = statusTrip;
        this.tripId = tripId;
        this.typeVehicle = typeVehicle;
    }

    public TripScheduleResponse(Long tripId) {
        this.tripId = tripId;
    }

    public Long getTripId() {
        return tripId;
    }

    public String getJustify() {
        return justify;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getStatusTrip() {
        return statusTrip;
    }

    public Long getIdDriver() {
        return idDriver;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getModelVehicle() {
        return modelVehicle;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public AddressDestinyEntity getAddress() {
        return address;
    }


   
}
