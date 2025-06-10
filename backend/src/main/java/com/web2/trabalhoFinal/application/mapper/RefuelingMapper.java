package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.refueling.RefuelingRequestDto;
import com.web2.trabalhoFinal.domain.model.refueling.Refueling;

public class RefuelingMapper {
    public static Refueling toDomain(RefuelingRequestDto dto) {
        return new Refueling(dto.id, dto.date, dto.typeRefueling, dto.price, dto.currentMileage, dto.driverName);
    }
}
