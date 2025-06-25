package com.web2.trabalhoFinal.application.controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.refueling.RefuelingRequestDto;
import com.web2.trabalhoFinal.application.dto.refueling.RefuelingResponse;
import com.web2.trabalhoFinal.application.mapper.RefuelingMapper;
import com.web2.trabalhoFinal.domain.model.Error.ResourceNotFoundException;
import com.web2.trabalhoFinal.domain.model.refueling.Refueling;
import com.web2.trabalhoFinal.domain.service.RefuelingService;


@RestController
@RequestMapping("/refueling")
public class RefuelingController {
    private RefuelingService refuelingService;

    public RefuelingController(RefuelingService refuelingService){
        this.refuelingService = refuelingService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RefuelingResponse>> registerRefuling(@RequestBody RefuelingRequestDto dto) throws Exception{
        try {
        Refueling refueling = RefuelingMapper.toDomain(dto);
        RefuelingResponse refuelingCreate= refuelingService.registerRefuling(refueling);
        String successMessage = "Abastecimento registrado";
        ApiResponse<RefuelingResponse> response = new ApiResponse<>(true, successMessage,refuelingCreate);
        return ResponseEntity.ok(response);       
        }catch (ResourceNotFoundException e) {
            ApiResponse<RefuelingResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);    
        } catch (Exception e) {
            ApiResponse<RefuelingResponse> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }  

    @GetMapping("/refuelings")
    public ResponseEntity<ApiResponse<List<RefuelingResponse>>> getAllRefuelings() {
        try {
            List<RefuelingResponse> refuelings = refuelingService.getAllRefuelings();
            String successMessage = "Abastecimentos recuperados";
            ApiResponse<List<RefuelingResponse>> response = new ApiResponse<>(true, successMessage,refuelings);
            return ResponseEntity.ok(response);
         } catch (Exception e) {
            ApiResponse<List<RefuelingResponse>> response = new ApiResponse<>(false, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRefueling(@PathVariable Long id) {
        try {
            ApiResponse<Void> response = refuelingService.deleteRefueling(id);
            return ResponseEntity.ok(response);
        
        } catch (Exception e) {
            String errorMessage = "Erro ao deletar o abastecimento: " + e.getMessage();
            ApiResponse<Void> response = new ApiResponse<>(false, errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }    
}   
