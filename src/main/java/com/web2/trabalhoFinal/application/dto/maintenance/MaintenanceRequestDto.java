package com.web2.trabalhoFinal.application.dto.maintenance;

import java.time.LocalDate;

public class MaintenanceRequestDto {
    public Long idVehicle;
    public LocalDate date;
    public String type;
    public String description;
    public Double price;
    public String currentMileage;
}
