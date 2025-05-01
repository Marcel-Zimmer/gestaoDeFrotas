package com.web2.trabalhoFinal.domain.service;

import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.domain.model.Driver;
import com.web2.trabalhoFinal.infrastructure.entity.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.repository.DriverRepository;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public void testando(){
        
    }
    public DriverEntity createDriver(Driver driver) {
        DriverEntity driverEntity = new DriverEntity(
            driver.getNameDriver(),            // Nome do motorista
            driver.getCpfDriver(),             // CPF do motorista
            driver.getCnhDriver(),             // CNH do motorista
            driver.getExpirationDateCnh(),     // Data de expiração da CNH
            driver.getPhoneNumberDriver(),     // Número de telefone do motorista
            driver.getEmailDriver(),           // Email do motorista
            driver.getPasswordDriver(),        // Senha do motorista (hashed)
            driver.getZipCode(),               // CEP do endereço
            driver.getStreet(),                // Logradouro do endereço
            driver.getComplement(),            // Complemento do endereço
            driver.getUnit(),                  // Unidade do endereço
            driver.getNeighborhood(),          // Bairro do endereço
            driver.getCity(),                  // Cidade do endereço
            driver.getStateAbbreviation(),     // Abreviação do estado
            driver.getState(),                 // Estado
            driver.getRegion(),                // Região
            driver.getIbgeCode(),              // Código IBGE
            driver.getGiaCode(),               // Código GIA
            driver.getDdd(),                   // DDD
            driver.getSiafiCode(),             // Código SIAFI
            driver.getNumberAddress()          // Número do endereço
        );
        
        return driverRepository.save(driverEntity);
    }
}