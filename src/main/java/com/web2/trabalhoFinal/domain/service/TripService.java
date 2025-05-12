package com.web2.trabalhoFinal.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web2.trabalhoFinal.application.dto.trip.TripScheduleResponse;
import com.web2.trabalhoFinal.domain.model.trip.Trip;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.trip.StatusTripEntity;
import com.web2.trabalhoFinal.infrastructure.entity.trip.TripEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.driver.DriverRepository;
import com.web2.trabalhoFinal.infrastructure.repository.trip.StatusTripRepository;
import com.web2.trabalhoFinal.infrastructure.repository.trip.TripRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.VehicleRepository;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;
    
    @Autowired
    private StatusTripRepository statusTripRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DriverRepository driverRepository;

    public TripScheduleResponse registerTrip(Trip trip) {
        VehicleEntity vehicle = vehicleRepository.getReferenceById(trip.getIdVehicle().getValue());
        DriverEntity driver = driverRepository.getReferenceById(trip.getIdDriver().getValue());
        StatusTripEntity status = statusTripRepository.findByStatus(trip.getStatus().getStatus())
        .orElseThrow(() -> new IllegalArgumentException("status não encontrado"));
        TripEntity newTrip = new TripEntity(vehicle, driver, status, trip.getDate(), trip.getTime(), trip.getJustify().getJustify());
        tripRepository.save(newTrip);
        return new TripScheduleResponse(true, "viagem agendada", newTrip.getId());



    }

}
