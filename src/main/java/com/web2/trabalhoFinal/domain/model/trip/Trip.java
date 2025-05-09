package com.web2.trabalhoFinal.domain.model.trip;

import java.time.LocalDate;
import java.time.LocalTime;

public class Trip {
    
    private ForeignKeyId idVehicle ;
    private ForeignKeyId IdDriver;
    private LocalDate date;
    private LocalTime time ;
    private JustifyTrip justify;
    private StatusTrip status;

    public Trip(){}
    
    public Trip(Long idVehicle, Long idDriver, LocalDate date, LocalTime time, String justify, String status) {
        this.idVehicle = new ForeignKeyId(idVehicle);
        IdDriver = new ForeignKeyId(idDriver);
        this.date = date;
        this.time = time;
        this.justify = new JustifyTrip(justify);
        this.status = new StatusTrip(status);
    }

    public ForeignKeyId getIdVehicle() {
        return idVehicle;
    }

    public ForeignKeyId getIdDriver() {
        return IdDriver;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public JustifyTrip getJustify() {
        return justify;
    }

    public StatusTrip getStatus() {
        return status;
    }
   
    
}
