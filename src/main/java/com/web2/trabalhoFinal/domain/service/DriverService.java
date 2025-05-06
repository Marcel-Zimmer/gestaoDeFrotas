package com.web2.trabalhoFinal.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.domain.model.Driver;
import com.web2.trabalhoFinal.infrastructure.entity.driver.AddressEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CnhEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CpfEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DddNumberEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.PhoneNumberEntity;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
import com.web2.trabalhoFinal.infrastructure.repository.driver.CnhRepository;
import com.web2.trabalhoFinal.infrastructure.repository.driver.CpfRepository;
import com.web2.trabalhoFinal.infrastructure.repository.driver.DriverRepository;
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
    


    public DriverEntity createDriver(Driver driver) {
        UserEntity user = new UserEntity(driver.getName(), driver.getEmail(), driver.getPassword().getHashedValue(), driver.isSuperUser(),driver.isAtive());
        PhoneNumberEntity phoneNumber = new PhoneNumberEntity(driver.getPhoneNumber().getPhoneValue());
        DddNumberEntity dddNumber = new DddNumberEntity(driver.getPhoneNumber().getDddValue());
        CpfEntity cpf = new CpfEntity(driver.getCpf().getValue());
        CnhEntity cnh = new CnhEntity(driver.getCnh().getValue(), driver.getCnh().getExpirationDate());
        AddressEntity address = new AddressEntity(driver.getAddress().getZipCode(), driver.getAddress().getStreet(), driver.getAddress().getComplement(), driver.getAddress().getUnit(),
        driver.getAddress().getNeighborhood(), driver.getAddress().getCity(), driver.getAddress().getStateAbbreviation(), driver.getAddress().getState(), driver.getAddress().getRegion(),
        driver.getAddress().getIbgeCode(), driver.getAddress().getGiaCode(), driver.getAddress().getDdd(), driver.getAddress().getSiafiCode(), driver.getAddress().getNumberAddress());
        DriverEntity newDriver = new DriverEntity(user,dddNumber,phoneNumber, cpf,cnh, address);
        userRepository.save(user);
        dddNumberRepository.save(dddNumber);
        phoneNumberRepository.save(phoneNumber);
        cpfRepository.save(cpf);
        cnhRepository.save(cnh);
        addressRepository.save(address);
        return driverRepository.save(newDriver);
    }
}
