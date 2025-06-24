package com.web2.trabalhoFinal.application.mapper;

import org.springframework.stereotype.Component;

import com.web2.trabalhoFinal.application.dto.driver.DriverRequestDto;
import com.web2.trabalhoFinal.application.dto.driver.DriverResponse;
import com.web2.trabalhoFinal.domain.model.Driver.Cnh;
import com.web2.trabalhoFinal.domain.model.Driver.Driver;
import com.web2.trabalhoFinal.domain.model.User.Address;
import com.web2.trabalhoFinal.domain.model.User.Cpf;
import com.web2.trabalhoFinal.domain.model.User.Email;
import com.web2.trabalhoFinal.domain.model.User.Name;
import com.web2.trabalhoFinal.domain.model.User.Password;
import com.web2.trabalhoFinal.domain.model.User.PhoneNumber;
import com.web2.trabalhoFinal.infrastructure.entity.driver.CnhEntity;
import com.web2.trabalhoFinal.infrastructure.entity.driver.DriverEntity;
import com.web2.trabalhoFinal.infrastructure.entity.user.AddressEntity;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;


@Component
public class DriverMapper {
    public static Driver toDomain(DriverRequestDto dto) {

        return new Driver(
            new Name(dto.name), 
            new Cpf(dto.cpf),  
            new Cnh(dto.cnh, dto.expirationDate),  
            new PhoneNumber(dto.phoneNumber, dto.dddNumber),  
            new Address(
                dto.zipCode, 
                dto.street, 
                dto.complement, 
                dto.unit, 
                dto.neighborhood, 
                dto.city, 
                dto.stateAbbreviation, 
                dto.state, 
                dto.region, 
                dto.ibgeCode, 
                dto.giaCode, 
                dto.ddd, 
                dto.siafiCode, 
                dto.numberAddress
            ),  
            new Email(dto.email),  
            new Password(dto.password)
        );
    }
    public static DriverEntity toEntity(Driver driverDomain) {
        if (driverDomain == null) {
            return null;
        }
        
        CnhEntity cnhEntity = new CnhEntity(
            driverDomain.getCnh().getValue(),
            driverDomain.getCnh().getExpirationDate()
        );
        
        AddressEntity addressEntity = new AddressEntity(
                driverDomain.getAddress().getZipCode(), 
                driverDomain.getAddress().getStreet(), 
                driverDomain.getAddress().getComplement(), 
                driverDomain.getAddress().getUnit(), 
                driverDomain.getAddress().getNeighborhood(), 
                driverDomain.getAddress().getCity(), 
                driverDomain.getAddress().getStateAbbreviation(), 
                driverDomain.getAddress().getState(), 
                driverDomain.getAddress().getRegion(), 
                driverDomain.getAddress().getIbgeCode(), 
                driverDomain.getAddress().getGiaCode(), 
                driverDomain.getAddress().getDdd(), 
                driverDomain.getAddress().getSiafiCode(), 
                driverDomain.getAddress().getNumberAddress()
        );
        UserEntity userEntity = new UserEntity(
            driverDomain.getName().getValue(),
            driverDomain.getEmail().getValue(),
            driverDomain.getPassword().getHashedValue(),
            driverDomain.getCpf().getValue(),
            driverDomain.getPhoneNumber().getPhoneValue(),
            addressEntity,
            driverDomain.isSuperUser(),
            driverDomain.isAtive()
        );

        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setUser(userEntity);
        driverEntity.setCnh(cnhEntity);
        driverEntity.setStatus(driverDomain.getStatus()); 

        return driverEntity;
    }  
    public static DriverResponse toResponseDto(DriverEntity entity) {
        if (entity == null) {
            return null;
        }
        return new DriverResponse(
            entity.getId(),
            entity.getUser().getName(),
            entity.getUser().getCpf(), 
            entity.getCnh().getCnh(),
            entity.getCnh().getDateExpiration(),
            entity.getUser().getEmail(),
            entity.getUser().getPhoneNumber(),
            entity.getUser().getAddress()
        );
    }
    /**
     * Atualiza uma entidade DriverEntity existente com dados de um objeto de domínio Driver.
     * @param driverDomain O objeto de domínio com os novos dados.
     * @param entity A entidade que foi CARREGADA do banco.
     */
    public static void updateFromDomain(Driver driverDomain, DriverEntity entity) {
        // Pega as referências para facilitar a leitura
        UserEntity user = entity.getUser();
        AddressEntity address = user.getAddress(); // O endereço está no User, não no DriverEntity
        CnhEntity cnh = entity.getCnh();

        // --- Atualização do UserEntity ---
        if (driverDomain.getName() != null) {
            user.setName(driverDomain.getName().getValue());
        }

        if (driverDomain.getEmail() != null) {
            user.setEmail(driverDomain.getEmail().getValue()); // CORRIGIDO
        }

        if (driverDomain.getCpf() != null) { // Validação de duplicidade deve ser feita no SERVICE
            user.setCpf(driverDomain.getCpf().getValue());
        }
        
        if (driverDomain.getPhoneNumber() != null) {
            user.setPhoneNumber(driverDomain.getPhoneNumber().getPhoneValue());
        }

        // --- Atualização do AddressEntity ---
        if (driverDomain.getAddress() != null) {
            Address newAddress = driverDomain.getAddress(); // Objeto de valor do endereço
            address.setZipCode(newAddress.getZipCode());
            address.setStreet(newAddress.getStreet());
            address.setComplement(newAddress.getComplement());
            address.setNumberAddress(newAddress.getNumberAddress());
            // ... etc para todos os outros campos do endereço
        }

        // --- Atualização do CnhEntity ---
        if (driverDomain.getCnh() != null) { // Validação de duplicidade deve ser feita no SERVICE
            Cnh newCnh = driverDomain.getCnh(); // Objeto de valor da CNH
            cnh.setCnh(newCnh.getValue()); // Supondo que o setter seja setCnhNumber
            cnh.setDateExpiration(newCnh.getExpirationDate());
        }
    }  
}           

