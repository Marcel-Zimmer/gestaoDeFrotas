package com.web2.trabalhoFinal.application.dto.driver;

public class DriverResponse {
    private boolean success;
    private String message;
    private Long driverId;
    
    public DriverResponse(boolean success, String message, Long driverId) {
        this.success = success;
        this.message = message;
        this.driverId = driverId;
    }

    public DriverResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getDriverId() {
        return driverId;
    }
}
