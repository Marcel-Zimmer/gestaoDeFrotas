package com.web2.trabalhoFinal.domain.model.maintenance;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Maintenance {
    private ForeignKeyId idVehicle;
    private DateMaintenance date;
    private TypeMaintenance type;
    private Description description;
    private Price price;
    private CurrentMileage currentMileage;

    public Maintenance(Long idVehicle,LocalDate date, String type, String description, Double price,
            String currentMileage) {
        this.idVehicle = new ForeignKeyId(idVehicle);
        this.date = new DateMaintenance(date);
        this.type = new TypeMaintenance(type);
        this.description = new Description(description);
        this.price = new Price(BigDecimal.valueOf(price));
        this.currentMileage = new CurrentMileage(currentMileage);
    }

    public ForeignKeyId getIdVehicle() {
        return idVehicle;
    }

    public DateMaintenance getDate() {
        return date;
    }

    public TypeMaintenance getType() {
        return type;
    }

    public Description getDescription() {
        return description;
    }

    public Price getPrice() {
        return price;
    }

    public CurrentMileage getCurrentMileage() {
        return currentMileage;
    }
}
