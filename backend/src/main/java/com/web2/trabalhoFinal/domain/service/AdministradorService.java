package com.web2.trabalhoFinal.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorResponse;
import com.web2.trabalhoFinal.application.mapper.AdministradorMapper;
import com.web2.trabalhoFinal.domain.model.Administrador.Administrador;
import com.web2.trabalhoFinal.domain.model.Error.DuplicateResourceException;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
import com.web2.trabalhoFinal.infrastructure.repository.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class AdministradorService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public AdministradorResponse createAdministrador(Administrador administrador) {
        if (userRepository.existsByEmail(administrador.getEmail().getValue())) {
            throw new DuplicateResourceException("Email já cadastrado.");
        }
        if (userRepository.existsByCpf(administrador.getCpf().getValue())) {
            throw new DuplicateResourceException("CPF já cadastrado.");
        }

        UserEntity administradorEntity = AdministradorMapper.toEntity(administrador);
        administradorEntity.setAdmin(true);
        UserEntity savedAdministrador = userRepository.save(administradorEntity);
        return new AdministradorResponse(savedAdministrador.getId());
    }

    public List<AdministradorResponse> getAllAdministradores() {
        List<UserEntity> administradores = userRepository.findByIsAdmin(true);

        return administradores.stream()
                      .map(AdministradorMapper::toResponseDto)
                      .collect(Collectors.toList());   
    }

    @Transactional
    public AdministradorResponse updateAdministrador(Long id, Administrador administrador) {
        UserEntity administradorEntity = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado."));
        
        if (!administradorEntity.getCpf().equals(administrador.getCpf().getValue())) {
            if (userRepository.existsByCpf(administrador.getCpf().getValue())) {
                throw new DuplicateResourceException("O cpf " + administrador.getCpf().getValue() + " já está cadastrada para outro usuário.");
            }
        }
        AdministradorMapper.updateFromDomain(administrador, administradorEntity);   
        return AdministradorMapper.toResponseDto(administradorEntity);     
    }

    @Transactional
    public ApiResponse<Void> deleteAdministrador(Long id) {
        UserEntity administradorEntity = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário com ID " + id + " não encontrado."));

        administradorEntity.setActive(!administradorEntity.isActive());
        userRepository.save(administradorEntity);
        String successMessage = "Administrador inativado";
        return new ApiResponse<>(true, successMessage, null);
    }

}
