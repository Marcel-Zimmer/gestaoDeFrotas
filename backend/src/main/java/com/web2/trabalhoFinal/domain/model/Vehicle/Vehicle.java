package com.web2.trabalhoFinal.domain.model.vehicle;

public class Vehicle {
    
    private LicencePlate licencePlate;
    private ModelVehicle modelVehicle;
    private TypeVehicle typeVehicle;
    private YearVehicle yearVehicle;
    private CurrentMileage currentMileage;
    private StatusVehicle statusVehicle;

    public Vehicle(String licence, String model, String type, String year, String mileage, String status){
        this.licencePlate = new LicencePlate(licence);
        this.modelVehicle = new ModelVehicle(model);
        this.typeVehicle = new TypeVehicle(type);
        this.yearVehicle = new YearVehicle(year);
        this.currentMileage = new CurrentMileage(mileage);
        this.statusVehicle = new StatusVehicle(status);
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
