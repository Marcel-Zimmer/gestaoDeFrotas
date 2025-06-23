package com.web2.trabalhoFinal.domain.model.trip;

import java.time.LocalDateTime;


public class Trip {
    
    private ForeignKeyId idVehicle ;
    private ForeignKeyId IdDriver;
    private LocalDateTime date;
    private JustifyTrip justify;
    private StatusTrip status;
    private AddressTrip destiny;

    public Trip(){}
    
    public Trip(Long idVehicle, Long idDriver, LocalDateTime date, String justify, String status, AddressTrip destiny) {
        this.idVehicle = new ForeignKeyId(idVehicle);
        IdDriver = new ForeignKeyId(idDriver);
        this.date = date;
        this.justify = new JustifyTrip(justify);
        this.status = new StatusTrip(status);
        this.destiny = destiny;
    }

    public ForeignKeyId getIdVehicle() {
        return idVehicle;
    }

    public ForeignKeyId getIdDriver() {
        return IdDriver;
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
   
    public AddressTrip getAddress(){
        return destiny;
    }
}
