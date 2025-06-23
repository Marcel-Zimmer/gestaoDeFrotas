package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.trip.TripScheduleRequestDto;
import com.web2.trabalhoFinal.domain.model.trip.AddressTrip;
import com.web2.trabalhoFinal.domain.model.trip.Trip;

public class TripMapper {
    public static Trip toDomain(TripScheduleRequestDto dto) {
        return new Trip(dto.idVehicle, dto.idDriver, dto.date, dto.justify,dto.status,new AddressTrip(dto.cep,dto.logradouro,dto.complemento, dto.unidade, dto.bairro,dto.localidade,dto.uf, dto.estado, dto.regiao, dto.ibge, dto.gia, dto.ddd, dto.siafi, dto.numero));
    }
}
