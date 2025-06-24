package com.web2.trabalhoFinal.application.mapper;
import com.web2.trabalhoFinal.application.dto.administrador.AdministradorRequestDto;
import com.web2.trabalhoFinal.domain.model.Administrador.Administrador;
import com.web2.trabalhoFinal.domain.model.User.Address;
import com.web2.trabalhoFinal.domain.model.User.Cpf;
import com.web2.trabalhoFinal.domain.model.User.Email;
import com.web2.trabalhoFinal.domain.model.User.Name;
import com.web2.trabalhoFinal.domain.model.User.Password;
import com.web2.trabalhoFinal.domain.model.User.PhoneNumber;
import com.web2.trabalhoFinal.domain.model.User.User;

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
}  

