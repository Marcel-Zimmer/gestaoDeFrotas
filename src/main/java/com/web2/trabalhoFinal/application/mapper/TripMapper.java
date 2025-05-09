package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.trip.TripScheduleRequestDto;
import com.web2.trabalhoFinal.domain.model.trip.Trip;

public class TripMapper {
    public static Trip toDomain(TripScheduleRequestDto dto) {
        return new Trip(dto.idVehicle, dto.idDriver, dto.date, dto.time, dto.justify,dto.status);
    }
}
