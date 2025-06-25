package com.web2.trabalhoFinal.domain.service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.application.mapper.DriverMapper;
import com.web2.trabalhoFinal.domain.model.Driver.Driver;
import com.web2.trabalhoFinal.domain.model.Driver.StatusDriver;
import com.web2.trabalhoFinal.domain.model.Error.BusinessRuleException;
import com.web2.trabalhoFinal.domain.model.Error.DuplicateResourceException;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CnhEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
import com.web2.trabalhoFinal.infrastructure.repository.driver.CnhRepository;
import com.web2.trabalhoFinal.infrastructure.repository.driver.DriverRepository;
import com.web2.trabalhoFinal.infrastructure.repository.user.UserRepository;

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
        List<DriverEntity> drivers = driverRepository.findAll();

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
        UserEntity userId = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado."));

        DriverEntity driverEntity = driverRepository.findByUserId(userId.getId());

        UserEntity userFind = userRepository.findByCpf(driver.getCpf().getValue());
        if(userFind != null && !Objects.equals(userFind.getId(), userId.getId())) {
            throw new ResourceNotFoundException("Cpf já cadastrada");
        }         
        userFind = userRepository.findByEmail(driver.getEmail().getValue());   
        if(userFind != null && !Objects.equals(userFind.getId(), userId.getId())) {
            throw new ResourceNotFoundException("Email já cadastrada");
        } 
        CnhEntity cnh = cnhRepository.findByCnh(driver.getCnh().getValue());
        if(userFind != null && !Objects.equals(driverEntity.getCnh().getId(), cnh.getId())) {
            throw new ResourceNotFoundException("Cnh já cadastrada");
        } 
        DriverMapper.updateFromDomain(driver, driverEntity);   
        return DriverMapper.toResponseDto(driverEntity);     
    }

    @Transactional
    public ApiResponse<Void> deleteDriver(Long id) {

        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado."));

        DriverEntity driverEntity = driverRepository.findByUserId(user.getId());
        
        // <<< ADICIONE ESTA VERIFICAÇÃO! >>>
        if (driverEntity == null) {
            // Isso acontece se o ID for de um usuário que não é motorista (ex: um administrador).
            throw new ResourceNotFoundException("Nenhum motorista encontrado associado ao usuário com ID " + id);
        }
        
        // O resto do seu código agora é seguro
        if (driverEntity.getStatus() == StatusDriver.EM_VIAGEM) {
            throw new BusinessRuleException("Não é possível inativar um motorista que está em viagem.");
        }       
        
        user.setActive(!user.isActive());

        String successMessage = "Motorista inativado com sucesso";
        return new ApiResponse<>(true, successMessage, null);
    }
}
