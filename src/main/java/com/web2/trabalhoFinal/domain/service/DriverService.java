package com.web2.trabalhoFinal.domain.service;

import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.domain.model.Driver;
import com.web2.trabalhoFinal.infrastructure.entity.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.repository.DriverRepository;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void testando(){
        
    }
    public DriverEntity createDriver(Driver motorista) {
        DriverEntity motoristaEntity = new DriverEntity(
            motorista.getNameDriver(),         
            motorista.getCpfDriver(),          
            motorista.getCnhDriver(),          
            motorista.getPhoneNumberDriver(),  
            motorista.getAdressDriver(),       
            motorista.getEmailDriver(),        
            motorista.getPasswordDriver()      
        );
        return driverRepository.save(motoristaEntity);
    }
}