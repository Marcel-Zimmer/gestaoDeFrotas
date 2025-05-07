package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.VehicleRequestDto;
import com.web2.trabalhoFinal.domain.model.Vehicle.Vehicle;

public class VehicleMapper {

    public static Vehicle toDomain(VehicleRequestDto dto) {
        return new Vehicle(dto.licence, dto.model, dto.type, dto.year, dto.mileage, dto.status);
    }

}
