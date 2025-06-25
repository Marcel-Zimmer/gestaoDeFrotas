package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.refueling.RefuelingRequestDto;
import com.web2.trabalhoFinal.application.dto.refueling.RefuelingResponse;
import com.web2.trabalhoFinal.domain.model.refueling.Refueling;
import com.web2.trabalhoFinal.infrastructure.entity.refueling.RefuelingEntity;
import com.web2.trabalhoFinal.infrastructure.entity.refueling.TypeRefuelingEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;

public class RefuelingMapper {
    public static Refueling toDomain(RefuelingRequestDto dto) {
        return new Refueling(dto.id, dto.date, dto.typeRefueling, dto.price, dto.currentMileage, dto.driverName);
    }
    public static RefuelingEntity toEntity(Refueling refuelingDomain, VehicleEntity vehicle, TypeRefuelingEntity typeRefueling) {
        RefuelingEntity entity = new RefuelingEntity(vehicle, typeRefueling, refuelingDomain.getNameDriver().getValue(),
        refuelingDomain.getDate().getValue(), refuelingDomain.getPrice().getValue(), refuelingDomain.getCurrentMileage().getValue());
        return entity;
        
    }
    public static RefuelingResponse toResponseDto(RefuelingEntity entity) {
        if (entity == null) {
            return null;
        }
        return new RefuelingResponse(entity.getId(), entity.getVehicle(), entity.getTypeRefueling().getTypeRefueling(), 
        entity.getNameDriver(), entity.getRefuelingDate(), entity.getPriceRefueling(), entity.getCurrentMileage());
    }    
}
