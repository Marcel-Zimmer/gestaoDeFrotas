package com.web2.trabalhoFinal.application.dto.vehicle;

import com.web2.trabalhoFinal.domain.model.Vehicle.LicencePlate;
import com.web2.trabalhoFinal.domain.model.Vehicle.ModelVehicle;
import com.web2.trabalhoFinal.domain.model.Vehicle.StatusVehicle;
import com.web2.trabalhoFinal.domain.model.Vehicle.TypeVehicle;
import com.web2.trabalhoFinal.domain.model.Vehicle.YearVehicle;
import com.web2.trabalhoFinal.domain.model.maintenance.CurrentMileage;

public class VehicleResponse {
    private Long vehicleId;
    private LicencePlate licencePlate;
    private ModelVehicle modelVehicle;
    private TypeVehicle typeVehicle;
    private YearVehicle yearVehicle;
    private CurrentMileage currentMileage;
    private StatusVehicle statusVehicle;
    

    public VehicleResponse(Long vehicleId) {
        this.vehicleId = vehicleId;

    }

    public VehicleResponse(Long vehicleId,String licence, String model, String type, String year, String mileage, String status){
        this.vehicleId =  vehicleId;
        this.licencePlate = new LicencePlate(licence);
        this.modelVehicle = new ModelVehicle(model);
        this.typeVehicle = new TypeVehicle(type);
        this.yearVehicle = new YearVehicle(year);
        this.currentMileage = new CurrentMileage(mileage);
        this.statusVehicle = new StatusVehicle(status);
    }    


    public Long getVehicleId() {
        return vehicleId;
    }

    public LicencePlate getLicencePlate() {
        return licencePlate;
    }

    public ModelVehicle getModelVehicle() {
        return modelVehicle;
    }

    public TypeVehicle getTypeVehicle() {
        return typeVehicle;
    }

    public YearVehicle getYearVehicle() {
        return yearVehicle;
    }

    public CurrentMileage getCurrentMileage() {
        return currentMileage;
    }

    public StatusVehicle getStatusVehicle() {
        return statusVehicle;
    }    

}
