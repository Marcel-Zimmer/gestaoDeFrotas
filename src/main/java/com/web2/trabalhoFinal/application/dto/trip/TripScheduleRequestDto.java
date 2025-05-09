package com.web2.trabalhoFinal.application.dto.trip;

import java.time.LocalDate;
import java.time.LocalTime;

public class TripScheduleRequestDto {
    public Long idVehicle;
    public Long idDriver;
    public LocalDate date;
    public LocalTime time;
    public String justify;
    public String status;
}
