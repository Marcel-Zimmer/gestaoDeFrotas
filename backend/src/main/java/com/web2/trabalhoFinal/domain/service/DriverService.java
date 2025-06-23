package com.web2.trabalhoFinal.domain.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.domain.model.Driver.Driver;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
import com.web2.trabalhoFinal.infrastructure.entity.driver.AddressEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CnhEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CpfEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DddNumberEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.PhoneNumberEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.StatusDriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
import com.web2.trabalhoFinal.infrastructure.repository.driver.CnhRepository;
import com.web2.trabalhoFinal.infrastructure.repository.driver.CpfRepository;
import com.web2.trabalhoFinal.infrastructure.repository.driver.DriverRepository;
import com.web2.trabalhoFinal.infrastructure.repository.driver.StatusDriverRepository;
import com.web2.trabalhoFinal.infrastructure.repository.user.AddressRepository;
import com.web2.trabalhoFinal.infrastructure.repository.user.DddNumberRepository;
import com.web2.trabalhoFinal.infrastructure.repository.user.PhoneNumberRepository;
import com.web2.trabalhoFinal.infrastructure.repository.user.UserRepository;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;
    @Autowired
    private CpfRepository cpfRepository;
    @Autowired
    private CnhRepository cnhRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private DddNumberRepository dddNumberRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatusDriverRepository statusDriverRepository;
    
    public DriverResponse createDriver(Driver driver) {
        UserEntity user = userRepository.findByEmail(driver.getEmail());
        if(user != null){
            throw new ResourceNotFoundException("email já cadastrado");
        }
        user = new UserEntity(driver.getName(), driver.getEmail(), driver.getPassword().getHashedValue(), driver.isSuperUser(),driver.isAtive());

        PhoneNumberEntity phoneNumber = phoneNumberRepository.findByPhoneNumber(driver.getPhoneNumber().getPhoneValue());
        if(phoneNumber != null){
            throw new ResourceNotFoundException("telefone já cadastrado");
        }
        phoneNumber = new PhoneNumberEntity(driver.getPhoneNumber().getPhoneValue());

        DddNumberEntity dddNumber = dddNumberRepository.findByDddNumber(driver.getPhoneNumber().getDddValue());
        if(dddNumber == null){
            dddNumber = new DddNumberEntity(driver.getPhoneNumber().getDddValue());
            dddNumberRepository.save(dddNumber);
        }
        CpfEntity cpf = cpfRepository.findByCpf(driver.getCpf().getValue());
        if(cpf != null){
            throw new ResourceNotFoundException("cpf já cadastrado");
        }
        cpf = new CpfEntity(driver.getCpf().getValue());

        CnhEntity cnh = cnhRepository.findByCnh(driver.getCnh().getValue());
        if(cnh != null){
            throw new ResourceNotFoundException("cnh já cadastrada");
        }
        cnh = new CnhEntity(driver.getCnh().getValue(), driver.getCnh().getExpirationDate());

        AddressEntity address = new AddressEntity(driver.getAddress().getZipCode(), driver.getAddress().getStreet(), driver.getAddress().getComplement(), driver.getAddress().getUnit(),
        driver.getAddress().getNeighborhood(), driver.getAddress().getCity(), driver.getAddress().getStateAbbreviation(), driver.getAddress().getState(), driver.getAddress().getRegion(),
        driver.getAddress().getIbgeCode(), driver.getAddress().getGiaCode(), driver.getAddress().getDdd(), driver.getAddress().getSiafiCode(), driver.getAddress().getNumberAddress());
        
        StatusDriverEntity statusFromDriver = statusDriverRepository.findByStatus(driver.getStatus().getStatus());
        if(statusFromDriver == null){
            statusFromDriver = new StatusDriverEntity(driver.getStatus().getStatus());
            statusDriverRepository.save(statusFromDriver);
        }

        DriverEntity newDriver = new DriverEntity(user,dddNumber,phoneNumber, cpf,cnh, address,statusFromDriver);
        
        addressRepository.save(address);
        cnhRepository.save(cnh);
        userRepository.save(user);
        phoneNumberRepository.save(phoneNumber);
        cpfRepository.save(cpf);
        driverRepository.save(newDriver);
    
        return new DriverResponse(newDriver.getId());
    }
    public List<DriverResponse> getAllDrivers() {
        List<DriverEntity> drivers = driverRepository.findAll();
        List<DriverResponse> response = new ArrayList<>(); 

        for (DriverEntity driver : drivers) {
            DriverResponse driverResponse = new DriverResponse(driver.getId(),driver.getUser().getName(),driver.getCpf().getCpf(),driver.getCnh().getCnh(),
            driver.getCnh().getDateExpiration(),driver.getUser().getEmail(),driver.getDddNumber().getDddNumber(),driver.getPhoneNumber().getPhoneNumber(),driver.getAddress());
             response.add(driverResponse);
        }
        return response;
    }

    public List<DriverResponse> getAllDisponibleDrivers() {
        List<DriverEntity> drivers = driverRepository.findByStatusStatus("DISPONIVEL");
        List<DriverResponse> response = new ArrayList<>(); 

        for (DriverEntity driver : drivers) {
            DriverResponse driverResponse = new DriverResponse(driver.getId(),driver.getUser().getName(),driver.getCpf().getCpf(),driver.getCnh().getCnh(),
            driver.getCnh().getDateExpiration(),driver.getUser().getEmail(),driver.getDddNumber().getDddNumber(),driver.getPhoneNumber().getPhoneNumber(),driver.getAddress());
             response.add(driverResponse);
        }
        return response;
    }
}
