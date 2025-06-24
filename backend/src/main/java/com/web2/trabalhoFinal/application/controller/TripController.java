package com.web2.trabalhoFinal.application.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.trip.TripScheduleRequestDto;
import com.web2.trabalhoFinal.application.dto.trip.TripScheduleResponse;
import com.web2.trabalhoFinal.application.mapper.TripMapper;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
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
    public ResponseEntity<ApiResponse<TripScheduleResponse>> registerSchedule(@RequestBody TripScheduleRequestDto dto) throws Exception{
        try {
            Trip trip = TripMapper.toDomain(dto);
            TripScheduleResponse tripCreate= tripService.registerTrip(trip);
            String sucess = "Agendamento criado com sucesso";
            ApiResponse<TripScheduleResponse> response = new ApiResponse<>(true, sucess,tripCreate);
            return ResponseEntity.ok(response);    

        }catch (Exception e) {
            ApiResponse<TripScheduleResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    } 
    
    @GetMapping("/schedules")
    public ResponseEntity<ApiResponse<List<TripScheduleResponse>>> getAllSchedules(){   
        try {
            List<TripScheduleResponse> schedules = tripService.getAllSchedules();
            String successMessage = "Agendamentos recuperados";
            ApiResponse<List<TripScheduleResponse>> response = new ApiResponse<>(true, successMessage,schedules);
            return ResponseEntity.ok(response);

         } catch (Exception e) {
            ApiResponse<List<TripScheduleResponse>> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<TripScheduleResponse>> updateVehicle (@PathVariable Long id,@RequestBody TripScheduleRequestDto dto) {
        try {
            Trip trip = TripMapper.toDomain(dto);
            ApiResponse<TripScheduleResponse> response= tripService.updateTrip(id,trip);
            return ResponseEntity.status(HttpStatus.OK).body(response); 

        }catch (ResourceNotFoundException e) {
            ApiResponse<TripScheduleResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    

        } catch (Exception e) {
            ApiResponse<TripScheduleResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteVehicle(@PathVariable Long id) {
        try {
            ApiResponse<Void> response = tripService.deleteTrip(id);
            return ResponseEntity.ok(response);
        
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar o agendamento: " + e.getMessage();
            ApiResponse<Void> response = new ApiResponse<>(false, errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }    
}
