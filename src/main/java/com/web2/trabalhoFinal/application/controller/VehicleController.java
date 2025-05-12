package com.web2.trabalhoFinal.application.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web2.trabalhoFinal.application.dto.vehicle.VehicleRequestDto;
import com.web2.trabalhoFinal.application.dto.vehicle.VehicleResponse;
import com.web2.trabalhoFinal.application.mapper.VehicleMapper;
import com.web2.trabalhoFinal.domain.model.vehicle.Vehicle;
import com.web2.trabalhoFinal.domain.service.VehicleService;

@RestController
@RequestMapping("/register")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle")
    public VehicleResponse registerVehicle(@RequestBody VehicleRequestDto dto) throws Exception{
        try {
            Vehicle vehicle = VehicleMapper.toDomain(dto);
            VehicleResponse vehicleCreate= vehicleService.registerVehicle(vehicle);
            return vehicleCreate;
        } catch (Exception e) {
            return new VehicleResponse(false, e.getMessage());
        }
    }  
}
