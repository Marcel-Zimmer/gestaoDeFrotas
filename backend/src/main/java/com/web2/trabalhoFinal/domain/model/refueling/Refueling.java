package com.web2.trabalhoFinal.domain.model.refueling;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Refueling {
    private ForeignKeyId VehicleId;
    private DateRefueling date;
    private TypeRefueling typeRefueling;
    private Price price;
    private CurrentMileage currentMileage;
    private DriverName nameDriver;
    
    public Refueling(Long vehicleId, LocalDate date, String typeRefueling, Double price,
            String currentMileage, String nameDriver) {
        VehicleId = new ForeignKeyId(vehicleId);
        this.date = new DateRefueling(date);
        this.typeRefueling = new TypeRefueling(typeRefueling);
        this.price = new Price(BigDecimal.valueOf(price));
        this.currentMileage = new CurrentMileage(currentMileage);
        this.nameDriver = new DriverName(nameDriver);
    }

    public ForeignKeyId getVehicleId() {
        return VehicleId;
    }

    public DateRefueling getDate() {
        return date;
    }

    public TypeRefueling getTypeRefueling() {
        return typeRefueling;
    }

    public Price getPrice() {
        return price;
    }

    public CurrentMileage getCurrentMileage() {
        return currentMileage;
    }

    public DriverName getNameDriver() {
        return nameDriver;
    }
    
}
