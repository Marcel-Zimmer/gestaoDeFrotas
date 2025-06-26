package com.web2.trabalhoFinal.application.mapper;

import java.time.LocalDateTime;

import com.web2.trabalhoFinal.application.dto.trip.TripScheduleRequestDto;
import com.web2.trabalhoFinal.domain.model.trip.AddressTrip;
import com.web2.trabalhoFinal.domain.model.trip.Trip;

public class TripMapper {
    public static Trip toDomain(TripScheduleRequestDto dto) {
        return new Trip(dto.idVehicle, dto.idDriver, dto.date, dto.justify,dto.status,new AddressTrip(dto.cep,dto.logradouro,dto.complemento, dto.unidade, dto.bairro,dto.localidade,dto.uf, dto.estado, dto.regiao, dto.ibge, dto.gia, dto.ddd, dto.siafi, dto.numero));
    }
    public static Trip startTrip(TripScheduleRequestDto dto){
        Trip trip = new Trip();
            trip.setActualDepartureTime(LocalDateTime.now());
            trip.setStartMileage(dto.startMileage);
            trip.setStartObservations(dto.startObservations);
        

        return trip;
    }

    public static Trip endTrip(TripScheduleRequestDto dto){
        Trip trip = new Trip();
            trip.setActualDepartureTime(LocalDateTime.now());
            trip.setEndMileage(dto.endMileage);
            trip.setEndObservations(dto.endObservations);
        return trip;
    }    
    
}
