package com.web2.trabalhoFinal.domain.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.trip.TripScheduleResponse;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
import com.web2.trabalhoFinal.domain.model.trip.Trip;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.trip.AddressDestinyEntity;
import com.web2.trabalhoFinal.infrastructure.entity.trip.StatusTripEntity;
import com.web2.trabalhoFinal.infrastructure.entity.trip.TripEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.driver.DriverRepository;
import com.web2.trabalhoFinal.infrastructure.repository.trip.AddressDestinyRepository;
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
    
    @Autowired
    private AddressDestinyRepository addressDestinyRepository;

    public TripScheduleResponse registerTrip(Trip trip) {
        VehicleEntity vehicle = vehicleRepository.getReferenceById(trip.getIdVehicle().getValue());
        DriverEntity driver = driverRepository.getReferenceById(trip.getIdDriver().getValue());
        StatusTripEntity status = statusTripRepository.findByStatus(trip.getStatus().getStatus());
        
        if(status == null){
            status  = new StatusTripEntity(trip.getStatus().getStatus());
            statusTripRepository.save(status);
        }
        AddressDestinyEntity destiny = new AddressDestinyEntity(trip.getAddress().getZipCode(), trip.getAddress().getStreet(), trip.getAddress().getComplement(),trip.getAddress().getUnit(), trip.getAddress().getNeighborhood(), trip.getAddress().getCity(), trip.getAddress().getStateAbbreviation(),trip.getAddress().getState(),trip.getAddress().getRegion(),trip.getAddress().getIbgeCode(),trip.getAddress().getGiaCode(), trip.getAddress().getDdd(), trip.getAddress().getSiafiCode(), trip.getAddress().getNumberAddress());
        addressDestinyRepository.save(destiny);
        TripEntity newTrip = new TripEntity(vehicle, driver, status, trip.getDate(), trip.getJustify().getJustify(),destiny);
        tripRepository.save(newTrip);
        return new TripScheduleResponse(newTrip.getId());
    }

    public List<TripScheduleResponse> getAllSchedules() {
        List<TripEntity> schedules = tripRepository.findAllWithDetails();
        List<TripScheduleResponse> response = new ArrayList<>(); 

        for (TripEntity schedule : schedules) {
            TripScheduleResponse scheduleResponse = new TripScheduleResponse(schedule.getDestiny(),schedule.getDate(), schedule.getDriver().getId(), schedule.getVehicle().getId(), schedule.getJustify(), schedule.getVehicle().getLicencePlate(), schedule.getVehicle().getModelVehicleEntity().getModelVehicle(), schedule.getDriver().getUser().getName(), schedule.getStatus().getStatus(), schedule.getId(), schedule.getVehicle().getTypeVehicleEntity().getTypeVehicle());
            response.add(scheduleResponse);
        }
        return response;
    }

    public ApiResponse<TripScheduleResponse> updateTrip(Long id, Trip trip) {


        TripEntity tripToUpdate = tripRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("id inválido"));

        VehicleEntity vehicle = vehicleRepository.findById(trip.getIdVehicle().getValue())
        .orElseThrow(() -> new ResourceNotFoundException("id de veiculo inválido"));

        DriverEntity driver  = driverRepository.findById(trip.getIdDriver().getValue())
        .orElseThrow(() -> new ResourceNotFoundException("id de motorista inválido"));

        StatusTripEntity statusTrip = statusTripRepository.findByStatus(trip.getStatus().getStatus());
        if(statusTrip == null){
            statusTrip = new StatusTripEntity(trip.getStatus().getStatus());
            statusTripRepository.save(statusTrip);
        } 

        AddressDestinyEntity destiny = new AddressDestinyEntity(trip.getAddress().getZipCode(), trip.getAddress().getStreet(), trip.getAddress().getComplement(), trip.getAddress().getComplement(), trip.getAddress().getNeighborhood(),
        trip.getAddress().getCity(), trip.getAddress().getStateAbbreviation(), trip.getAddress().getState(), trip.getAddress().getRegion(), trip.getAddress().getIbgeCode(), trip.getAddress().getGiaCode(), trip.getAddress().getDdd(), trip.getAddress().getSiafiCode(), trip.getAddress().getNumberAddress());
        addressDestinyRepository.save(destiny);

        tripToUpdate.setDate(trip.getDate());
        tripToUpdate.setDestiny(destiny);
        tripToUpdate.setDriver(driver);
        tripToUpdate.setVehicle(vehicle);
        tripToUpdate.setStatus(statusTrip);
        tripToUpdate.setJustify(trip.getJustify().getJustify());
        tripRepository.save(tripToUpdate);

        String successMessage = "Agendamento atualizado";
        ApiResponse<TripScheduleResponse> response = new ApiResponse<>(true, successMessage, new TripScheduleResponse(id));
        return response;         

    }

    public ApiResponse<Void> deleteTrip(Long id) {
        tripRepository.deleteById(id);
        String successMessage = "Agendamento deletado";
        return new ApiResponse<>(true, successMessage, null);
    }

}
