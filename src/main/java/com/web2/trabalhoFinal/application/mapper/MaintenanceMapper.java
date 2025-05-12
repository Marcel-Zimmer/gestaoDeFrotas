package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.maintenance.MaintenanceRequestDto;
import com.web2.trabalhoFinal.domain.model.maintenance.Maintenance;

public class MaintenanceMapper {
    public static Maintenance toDomain(MaintenanceRequestDto dto) {
        return new Maintenance(dto.idVehicle,dto.date, dto.type, dto.description, dto.price, dto.currentMileage);
    }
}
