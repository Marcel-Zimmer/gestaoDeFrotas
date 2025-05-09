package com.web2.trabalhoFinal.application.dto.vehicle;

public class VehicleResponse {
    private boolean success;
    private String message;
    private Long vehicleId;

    public VehicleResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public VehicleResponse(boolean success, String message, Long vehicleId) {
        this.success = success;
        this.message = message;
        this.vehicleId = vehicleId;

    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getVehicleId() {
        return vehicleId;
    }    

}
