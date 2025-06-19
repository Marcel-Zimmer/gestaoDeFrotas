package com.web2.trabalhoFinal.application.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.trip.TripScheduleRequestDto;
import com.web2.trabalhoFinal.application.dto.trip.TripScheduleResponse;
import com.web2.trabalhoFinal.application.mapper.TripMapper;
import com.web2.trabalhoFinal.domain.model.trip.Trip;
import com.web2.trabalhoFinal.domain.service.TripService;


@RestController
@RequestMapping("/trip")
public class TripController {

    private TripService tripService;

    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @PostMapping("/schedule")
    public TripScheduleResponse registerSchedule(@RequestBody TripScheduleRequestDto dto) throws Exception{
        try {
            Trip trip = TripMapper.toDomain(dto);
            TripScheduleResponse tripCreate= tripService.registerTrip(trip);
            return tripCreate;   
        } catch (Exception e) {
            return null;
        }

    } 
    
    @GetMapping("/schedules")
    public ResponseEntity<ApiResponse<List<TripScheduleResponse>>> getAllSchedules(){   
        try {
            List<TripScheduleResponse> schedules = tripService.getAllVehicles();
            String successMessage = "Agendamentos recuperados";
            ApiResponse<List<TripScheduleResponse>> response = new ApiResponse<>(true, successMessage,schedules);
            return ResponseEntity.ok(response);
         } catch (Exception e) {
            ApiResponse<List<TripScheduleResponse>> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
