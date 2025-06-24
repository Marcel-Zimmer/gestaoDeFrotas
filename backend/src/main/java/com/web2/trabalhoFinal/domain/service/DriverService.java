package com.web2.trabalhoFinal.domain.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.application.mapper.DriverMapper;
import com.web2.trabalhoFinal.domain.model.Driver.Driver;
import com.web2.trabalhoFinal.domain.model.Driver.StatusDriver;
import com.web2.trabalhoFinal.domain.model.Error.DuplicateResourceException;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.repository.driver.CnhRepository;
import com.web2.trabalhoFinal.infrastructure.repository.driver.DriverRepository;
import com.web2.trabalhoFinal.infrastructure.repository.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private CnhRepository cnhRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Transactional 
    public DriverResponse createDriver(Driver driverDomain) {
        if (userRepository.existsByEmail(driverDomain.getEmail().getValue())) {
            throw new DuplicateResourceException("Email já cadastrado.");
        }
        if (userRepository.existsByCpf(driverDomain.getCpf().getValue())) {
            throw new DuplicateResourceException("CPF já cadastrado.");
        }
        if (cnhRepository.existsByCnh(driverDomain.getCnh().getValue())) {
            throw new DuplicateResourceException("CNH já cadastrada.");
        }

        DriverEntity driverEntity = DriverMapper.toEntity(driverDomain);
        DriverEntity savedDriver = driverRepository.save(driverEntity);
        return new DriverResponse(savedDriver.getId());
    }
    public List<DriverResponse> getAllDrivers() {
        List<DriverEntity> drivers = driverRepository.findAllWithDetails();

        return drivers.stream()
                      .map(DriverMapper::toResponseDto) 
                      .collect(Collectors.toList());    
    }

    public List<DriverResponse> getAllDisponibleDrivers() {
        List<DriverEntity> availableDrivers = driverRepository.findByStatusWithDetails(StatusDriver.DISPONIVEL);

        return availableDrivers.stream()
                               .map(DriverMapper::toResponseDto)
                               .collect(Collectors.toList());
    }
}
