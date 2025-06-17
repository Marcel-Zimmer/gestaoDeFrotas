package com.web2.trabalhoFinal.application.dto.vehicle;

public class VehicleResponse {
    private Long vehicleId;
    private String licencePlate;
    private String modelVehicle;
    private String typeVehicle;
    private String yearVehicle;
    private String currentMileage;
    private String statusVehicle;
    

    public VehicleResponse(Long vehicleId) {
        this.vehicleId = vehicleId;

    }

    public VehicleResponse(Long vehicleId,String licence, String model, String type, String year, String mileage, String status){
        this.vehicleId =  vehicleId;
        this.licencePlate = licence;
        this.modelVehicle = model;
        this.typeVehicle = type;
        this.yearVehicle = year;
        this.currentMileage = mileage;
        this.statusVehicle = status;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public String getModelVehicle() {
        return modelVehicle;
    }

    public String getTypeVehicle() {
        return typeVehicle;
    }

    public String getYearVehicle() {
        return yearVehicle;
    }

    public String getCurrentMileage() {
        return currentMileage;
    }

    public String getStatusVehicle() {
        return statusVehicle;
    }    


   

}
