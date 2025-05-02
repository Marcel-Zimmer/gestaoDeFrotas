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
    public Boolean createDriver(Driver driver) {
        DriverEntity driverEntity = new DriverEntity(
            driver.getName(),            
            driver.getCpf(),             
            driver.getCnh(),               
            driver.getPhoneNumber(),
            driver.getAddress(),     
            driver.getEmail(),           
            driver.getPassword(),        
            driver.isSuperUser()            
        );
        DriverEntity driverCreate= driverRepository.save(driverEntity);
        if(driverCreate != null){
            return true;
        }
        return false;
    }
}