package com.web2.trabalhoFinal.domain.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.application.mapper.DriverMapper;
import com.web2.trabalhoFinal.domain.model.Driver.Driver;
import com.web2.trabalhoFinal.domain.model.Driver.StatusDriver;
import com.web2.trabalhoFinal.domain.model.Error.BusinessRuleException;
import com.web2.trabalhoFinal.domain.model.Error.DuplicateResourceException;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
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
        List<DriverEntity> availableDrivers = driverRepository.findActiveAndAvailableWithDetails();

        return availableDrivers.stream()
                               .map(DriverMapper::toResponseDto)
                               .collect(Collectors.toList());
    }

    @Transactional 
    public DriverResponse updateDriver(Long id, Driver driver){
        DriverEntity driverEntity = driverRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado."));

        if (!driverEntity.getCnh().getCnh().equals(driver.getCnh().getValue())) {
            if (cnhRepository.existsByCnh(driver.getCnh().getValue())) {
                throw new DuplicateResourceException("A CNH " + driver.getCnh().getValue() + " já está cadastrada para outro motorista.");
            }
        }
        if (!driverEntity.getUser().getCpf().equals(driver.getCpf().getValue())) {
            if (userRepository.existsByCpf(driver.getCpf().getValue())) {
                throw new DuplicateResourceException("O cpf " + driver.getCpf().getValue() + " já está cadastrada para outro motorista.");
            }
        }
        DriverMapper.updateFromDomain(driver, driverEntity);   
        return DriverMapper.toResponseDto(driverEntity);     
    }

    @Transactional
    public ApiResponse<Void> deleteDriver(Long id) {
        DriverEntity driverEntity = driverRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado."));
            
        if (driverEntity.getStatus() == StatusDriver.EM_VIAGEM) {
            throw new BusinessRuleException("Não é possível inativar um motorista que está em viagem.");
        }        
        driverEntity.getUser().setActive(false);
        String successMessage = "Motorista inativado";
        return new ApiResponse<>(true, successMessage, null);
    }
}
