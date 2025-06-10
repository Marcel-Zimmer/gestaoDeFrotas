package com.web2.trabalhoFinal.application.dto.refueling;

public class RefuelingResponse {
    private boolean success;
    private String message;
    private Long refuelingResponse;
    
    public RefuelingResponse(boolean success, String message, Long refuelingResponse) {
        this.success = success;
        this.message = message;
        this.refuelingResponse = refuelingResponse;
    }

    public RefuelingResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getRefuelingId() {
        return refuelingResponse;
    }
}
