package com.web2.trabalhoFinal.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.trip.TripScheduleResponse;
import com.web2.trabalhoFinal.domain.model.trip.Trip;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.trip.StatusTripEntity;
import com.web2.trabalhoFinal.infrastructure.entity.trip.TripEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.trip.StatusTripRepository;
import com.web2.trabalhoFinal.infrastructure.repository.trip.TripRepository;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private StatusTripRepository statusTripRepository;

    public TripScheduleResponse registerTrip(Trip trip) {
        VehicleEntity vehicle = new VehicleEntity(trip.getIdVehicle().getValue());
        DriverEntity driver = new DriverEntity(trip.getIdDriver().getValue());
        StatusTripEntity status = new StatusTripEntity(trip.getStatus().getStatus());
        TripEntity newTrip = new TripEntity(vehicle, driver, status, trip.getDate(), trip.getTime(), trip.getJustify().getJustify());
        statusTripRepository.save(status);
        tripRepository.save(newTrip);
        return new TripScheduleResponse(true, "viagem agendada", newTrip.getId());

    }

}
