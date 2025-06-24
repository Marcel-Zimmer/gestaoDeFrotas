package com.web2.trabalhoFinal.application.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorRequestDto;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorResponse;
import com.web2.trabalhoFinal.application.mapper.AdministradorMapper;


import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
import com.web2.trabalhoFinal.domain.model.User.User;
import com.web2.trabalhoFinal.domain.service.AdministradorService;



@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    private AdministradorService administradorService;

    public AdministradorController(AdministradorService administradorService){
        this.administradorService = administradorService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AdministradorResponse>> registerAdministrador(@RequestBody AdministradorRequestDto dto) {
        try {
            User administrador = AdministradorMapper.toDomain(dto);
            AdministradorResponse createAdministrador = administradorService.createAdministrador(administrador);
            String sucess = "Administrador criado com sucesso";
            ApiResponse<AdministradorResponse> response = new ApiResponse<>(true, sucess,createAdministrador);
            return ResponseEntity.ok(response);    

        }catch (ResourceNotFoundException e) {
            ApiResponse<AdministradorResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    
        } catch (Exception e) {
            ApiResponse<AdministradorResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


}