package com.web2.trabalhoFinal.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Trip trip = TripMapper.toDomain(dto);
        TripScheduleResponse tripCreate= tripService.registerTrip(trip);
        return tripCreate;
    }  
}
