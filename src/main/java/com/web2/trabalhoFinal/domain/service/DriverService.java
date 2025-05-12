package com.web2.trabalhoFinal.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.domain.model.driver.Driver;
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
    public DriverResponse createDriver(Driver driver) {
        UserEntity user = new UserEntity(driver.getName(), driver.getEmail(), driver.getPassword().getHashedValue(), driver.isSuperUser(),driver.isAtive());
        userRepository.save(user);
        PhoneNumberEntity phoneNumber = new PhoneNumberEntity(driver.getPhoneNumber().getPhoneValue());
        phoneNumberRepository.save(phoneNumber);
        DddNumberEntity dddNumber = dddNumberRepository.findByDddNumber(driver.getPhoneNumber().getDddValue());
        if(dddNumber == null){
            dddNumber = new DddNumberEntity(driver.getPhoneNumber().getDddValue());
            dddNumberRepository.save(dddNumber);
        }
        CpfEntity cpf = new CpfEntity(driver.getCpf().getValue());
        cpfRepository.save(cpf);
        CnhEntity cnh = new CnhEntity(driver.getCnh().getValue(), driver.getCnh().getExpirationDate());
        cnhRepository.save(cnh);
        AddressEntity address = new AddressEntity(driver.getAddress().getZipCode(), driver.getAddress().getStreet(), driver.getAddress().getComplement(), driver.getAddress().getUnit(),
        driver.getAddress().getNeighborhood(), driver.getAddress().getCity(), driver.getAddress().getStateAbbreviation(), driver.getAddress().getState(), driver.getAddress().getRegion(),
        driver.getAddress().getIbgeCode(), driver.getAddress().getGiaCode(), driver.getAddress().getDdd(), driver.getAddress().getSiafiCode(), driver.getAddress().getNumberAddress());
        addressRepository.save(address);
        DriverEntity newDriver = new DriverEntity(user,dddNumber,phoneNumber, cpf,cnh, address);
        driverRepository.save(newDriver);
        return new DriverResponse(true, "Motorista criado com sucesso", newDriver.getId());
    }
}
