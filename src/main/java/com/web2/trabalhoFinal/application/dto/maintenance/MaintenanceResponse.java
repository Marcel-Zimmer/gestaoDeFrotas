package com.web2.trabalhoFinal.application.dto.maintenance;

public class MaintenanceResponse {
    private boolean success;
    private String message;
    private Long maintenanceId;
    
    public MaintenanceResponse(boolean success, String message, Long maintenanceId) {
        this.success = success;
        this.message = message;
        this.maintenanceId = maintenanceId;
    }

    public MaintenanceResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getMaintenanceId() {
        return maintenanceId;
    }
}