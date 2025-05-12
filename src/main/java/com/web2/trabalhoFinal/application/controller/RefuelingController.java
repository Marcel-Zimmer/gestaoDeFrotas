package com.web2.trabalhoFinal.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web2.trabalhoFinal.application.dto.refueling.RefuelingRequestDto;
import com.web2.trabalhoFinal.application.dto.refueling.RefuelingResponse;
import com.web2.trabalhoFinal.application.mapper.RefuelingMapper;
import com.web2.trabalhoFinal.domain.model.refueling.Refueling;
import com.web2.trabalhoFinal.domain.service.RefuelingService;


@RestController
@RequestMapping("/refueling")
public class RefuelingController {
    private RefuelingService refuelingService;

    public RefuelingController(RefuelingService refuelingService){
        this.refuelingService = refuelingService;
    }

    @PostMapping("/register")
    public RefuelingResponse registerRefuling(@RequestBody RefuelingRequestDto dto) throws Exception{
        try {
        Refueling refueling = RefuelingMapper.toDomain(dto);
        RefuelingResponse refuelingCreate= refuelingService.registerRefuling(refueling);
        return refuelingCreate; 
        
        } catch (Exception e) {
           return new RefuelingResponse(false, e.getMessage());
        }

    }  
}   
