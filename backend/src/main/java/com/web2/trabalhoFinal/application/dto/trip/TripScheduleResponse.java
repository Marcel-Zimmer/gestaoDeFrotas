package com.web2.trabalhoFinal.application.dto.trip;

import java.time.LocalDate;


public class TripScheduleResponse {
    private Long tripId;
    private String justify;
    private LocalDate date;
    private String statusTrip;
    private String nameDriver;
    private String licencePlate;
    private String modelVehicle;
    private String typeVehicle;

    public TripScheduleResponse(Long tripId, String justify, LocalDate date, String nameDriver, String statusTrip,
            String licencePlate, String modelVehicle, String typeVehicle) {
        this.tripId = tripId;
        this.justify = justify;
        this.date = date;
        this.nameDriver = nameDriver;
        this.statusTrip = statusTrip;
        this.licencePlate = licencePlate;
        this.modelVehicle = modelVehicle;
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

    public LocalDate getDate() {
        return date;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public String getStatusTrip() {
        return statusTrip;
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
   
}
