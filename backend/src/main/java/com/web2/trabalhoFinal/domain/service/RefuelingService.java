package com.web2.trabalhoFinal.domain.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.ApiResponse;
import com.web2.trabalhoFinal.application.dto.refueling.RefuelingResponse;
import com.web2.trabalhoFinal.application.mapper.RefuelingMapper;
import com.web2.trabalhoFinal.domain.model.refueling.Refueling;
import com.web2.trabalhoFinal.infrastructure.entity.refueling.RefuelingEntity;
import com.web2.trabalhoFinal.infrastructure.entity.refueling.TypeRefuelingEntity;
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

        TypeRefuelingEntity typeRefueling = typeRefuelingRepository.findByTypeRefueling(refueling.getTypeRefueling().getRefueling());
        if(typeRefueling == null){
            typeRefueling = new TypeRefuelingEntity(refueling.getTypeRefueling().getRefueling());
            typeRefuelingRepository.save(typeRefueling);
        }


        RefuelingEntity refuelingEntity = new RefuelingEntity(vehicleEntity, typeRefueling, refueling.getNameDriver().getValue(), refueling.getDate().getValue(),refueling.getPrice().getValue(), refueling.getCurrentMileage().getValue());
        refuelingRepository.save(refuelingEntity);
        return new RefuelingResponse(refuelingEntity.getId());
    }

    public List<RefuelingResponse> getAllRefuelings() {
        List<RefuelingEntity> refuelings = refuelingRepository.findAllWithVehicleAndTypeDetails();

        return refuelings.stream()
                               .map(RefuelingMapper::toResponseDto)
                               .collect(Collectors.toList());
    }

    public ApiResponse<Void> deleteRefueling(Long id) {
        refuelingRepository.deleteById(id);
        String successMessage = "Abastecimento deletado";
        return new ApiResponse<>(true, successMessage, null);
    }

}
