package com.web2.trabalhoFinal.application.mapper;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorRequestDto;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorResponse;
import com.web2.trabalhoFinal.domain.model.Administrador.Administrador;
import com.web2.trabalhoFinal.domain.model.User.Address;
import com.web2.trabalhoFinal.domain.model.User.Cpf;
import com.web2.trabalhoFinal.domain.model.User.Email;
import com.web2.trabalhoFinal.domain.model.User.Name;
import com.web2.trabalhoFinal.domain.model.User.Password;
import com.web2.trabalhoFinal.domain.model.User.PhoneNumber;
import com.web2.trabalhoFinal.infrastructure.entity.user.AddressEntity;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;

public class AdministradorMapper {
    public static Administrador toDomain(AdministradorRequestDto dto) {

        return new Administrador(
            new Name(dto.name), 
            new Email(dto.email), 
            new Password("Ufpr2025!"), 
            true, 
            true, 
            new PhoneNumber(dto.phoneNumber,dto.ddd), 
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
            new Cpf(dto.cpf));
    }
    
    public static UserEntity toEntity(Administrador administrador) {
        if (administrador == null) {
            return null;
        }
        
        AddressEntity addressEntity = new AddressEntity(
                administrador.getAddress().getZipCode(), 
                administrador.getAddress().getStreet(), 
                administrador.getAddress().getComplement(), 
                administrador.getAddress().getUnit(), 
                administrador.getAddress().getNeighborhood(), 
                administrador.getAddress().getCity(), 
                administrador.getAddress().getStateAbbreviation(), 
                administrador.getAddress().getState(), 
                administrador.getAddress().getRegion(), 
                administrador.getAddress().getIbgeCode(), 
                administrador.getAddress().getGiaCode(), 
                administrador.getAddress().getDdd(), 
                administrador.getAddress().getSiafiCode(), 
                administrador.getAddress().getNumberAddress()
        );
        UserEntity userEntity = new UserEntity(
            administrador.getName().getValue(),
            administrador.getEmail().getValue(),
            administrador.getPassword().getHashedValue(),
            administrador.getCpf().getValue(),
            administrador.getPhoneNumber().getPhoneValue(),
            addressEntity,
            administrador.isSuperUser(),
            administrador.isAtive()
        );

        return userEntity;
    }  
    public static AdministradorResponse toResponseDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return new AdministradorResponse(
            entity.getId(),
            entity.getName(),
            entity.getCpf(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getAddress()
        );
    }  
    public static void updateFromDomain(Administrador driverDomain, UserEntity user) {

        AddressEntity address = user.getAddress(); // O endereço está no User, não no DriverEntity

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
    }         
}  

