package com.web2.trabalhoFinal.application.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorRequestDto;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorResponse;
import com.web2.trabalhoFinal.application.mapper.AdministradorMapper;
import com.web2.trabalhoFinal.domain.model.Administrador.Administrador;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
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
            Administrador administrador = AdministradorMapper.toDomain(dto);
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

    @GetMapping("/administradores")
    public ResponseEntity<ApiResponse<List<AdministradorResponse>>> getAllAdministradores() {
        try {
            List<AdministradorResponse> administradores = administradorService.getAllAdministradores();
            String successMessage = "Administradores recuperados";
            ApiResponse<List<AdministradorResponse>> response = new ApiResponse<>(true, successMessage,administradores);
            return ResponseEntity.ok(response);  

        } catch (Exception e) {
            var response = new ApiResponse<List<AdministradorResponse>>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("update/{id}")
    public ResponseEntity<ApiResponse<AdministradorResponse>> updateAdministrador (@PathVariable Long id,@RequestBody AdministradorRequestDto dto) {
        try {
            Administrador administrador = AdministradorMapper.toDomain(dto);
            AdministradorResponse responseUpdate= administradorService.updateAdministrador(id,administrador);
            String successMessage = "Administradore atualizado";
            ApiResponse<AdministradorResponse> response = new ApiResponse<>(true, successMessage,responseUpdate);
            return ResponseEntity.status(HttpStatus.OK).body(response);          
        }catch (ResourceNotFoundException e) {
            ApiResponse<AdministradorResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    
        } catch (Exception e) {
            ApiResponse<AdministradorResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
     @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAdministrador(@PathVariable Long id) {
        try {
            ApiResponse<Void> response = administradorService.deleteAdministrador(id);
            return ResponseEntity.ok(response);
        
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar o Administrador: " + e.getMessage();
            ApiResponse<Void> response = new ApiResponse<>(false, errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }    
}