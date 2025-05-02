package com.web2.trabalhoFinal.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.domain.model.Driver;
import com.web2.trabalhoFinal.infrastructure.entity.driver.AddressEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CnhEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CpfEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.PhoneNumberEntity;
import com.web2.trabalhoFinal.infrastructure.repository.AddressRepository;
import com.web2.trabalhoFinal.infrastructure.repository.CnhRepository;
import com.web2.trabalhoFinal.infrastructure.repository.CpfRepository;
import com.web2.trabalhoFinal.infrastructure.repository.DriverRepository;
import com.web2.trabalhoFinal.infrastructure.repository.PhoneNumberRepository;

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


    public DriverEntity createDriver(Driver driver) {

        PhoneNumberEntity phoneNumber = new PhoneNumberEntity(driver.getPhoneNumber().getPhoneValue(), driver.getPhoneNumber().getDddValue());
        CpfEntity cpf = new CpfEntity(driver.getCpf().getValue());
        CnhEntity cnh = new CnhEntity(driver.getCnh().getValue(), driver.getCnh().getExpirationDate());
        AddressEntity address = new AddressEntity(driver.getAddress().getZipCode(), driver.getAddress().getStreet(), driver.getAddress().getComplement(), driver.getAddress().getUnit(),
        driver.getAddress().getNeighborhood(), driver.getAddress().getCity(), driver.getAddress().getStateAbbreviation(), driver.getAddress().getState(), driver.getAddress().getRegion(),
        driver.getAddress().getIbgeCode(), driver.getAddress().getGiaCode(), driver.getAddress().getDdd(), driver.getAddress().getSiafiCode(), driver.getAddress().getNumberAddress());
        DriverEntity newDriver = new DriverEntity(driver.getName(), driver.getEmail(), driver.getPassword(), driver.isSuperUser(), phoneNumber, cpf,cnh, address);
        
        phoneNumberRepository.save(phoneNumber);
        cpfRepository.save(cpf);
        cnhRepository.save(cnh);
        addressRepository.save(address);

        return driverRepository.save(newDriver);
    }
}
