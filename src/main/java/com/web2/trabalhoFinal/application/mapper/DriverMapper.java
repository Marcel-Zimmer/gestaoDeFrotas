package com.web2.trabalhoFinal.application.mapper;

import com.web2.trabalhoFinal.application.dto.driver.DriverRequestDto;
import com.web2.trabalhoFinal.domain.model.driver.Address;
import com.web2.trabalhoFinal.domain.model.driver.Cnh;
import com.web2.trabalhoFinal.domain.model.driver.Cpf;
import com.web2.trabalhoFinal.domain.model.driver.Driver;
import com.web2.trabalhoFinal.domain.model.driver.PhoneNumber;

public class DriverMapper {
    public static Driver toDomain(DriverRequestDto dto) {

        return new Driver(
            dto.name, 
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
            dto.email,  
            dto.password, 
            dto.isSuperUser,
            dto.isAtive
        );
    }
}           

