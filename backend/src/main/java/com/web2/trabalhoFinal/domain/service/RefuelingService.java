package com.web2.trabalhoFinal.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.refueling.RefuelingResponse;
import com.web2.trabalhoFinal.domain.model.refueling.Refueling;
import com.web2.trabalhoFinal.infrastructure.entity.refueling.RefuelingEntity;
import com.web2.trabalhoFinal.infrastructure.entity.refueling.TypeRefuelingEntity;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
import com.web2.trabalhoFinal.infrastructure.entity.vehicle.VehicleEntity;
import com.web2.trabalhoFinal.infrastructure.repository.refueling.RefuelingRepository;
import com.web2.trabalhoFinal.infrastructure.repository.refueling.TypeRefuelingRepository;
import com.web2.trabalhoFinal.infrastructure.repository.user.UserRepository;
import com.web2.trabalhoFinal.infrastructure.repository.vehicle.VehicleRepository;

@Service
public class RefuelingService {
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    TypeRefuelingRepository typeRefuelingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefuelingRepository refuelingRepository;

    public RefuelingResponse registerRefuling(Refueling refueling) {
        VehicleEntity vehicleEntity = vehicleRepository.findById(refueling.getVehicleId().getValue())
        .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado"));
        TypeRefuelingEntity typeRefuelingEntity = typeRefuelingRepository.findByTypeRefueling(refueling.getTypeRefueling().getRefueling())
        .orElseThrow(() -> new IllegalArgumentException("Tipo de combustivel inválido"));
        UserEntity userEntity = userRepository.findByName(refueling.getNameDriver().getValue());
        if(userEntity == null){
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        RefuelingEntity refuelingEntity = new RefuelingEntity(vehicleEntity, typeRefuelingEntity, userEntity, refueling.getDate().getValue(),refueling.getPrice().getValue(), refueling.getCurrentMileage().getValue());
        refuelingRepository.save(refuelingEntity);
        return new RefuelingResponse(true, "abastecimento registrado", refuelingEntity.getId());
    }

}
