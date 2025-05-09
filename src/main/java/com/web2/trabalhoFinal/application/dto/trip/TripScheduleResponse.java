package com.web2.trabalhoFinal.application.dto.trip;


public class TripScheduleResponse {
    private boolean success;
    private String message;
    private Long tripId;
    
    public TripScheduleResponse(boolean success, String message, Long tripId) {
        this.success = success;
        this.message = message;
        this.tripId = tripId;
    }

    public TripScheduleResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getTripId() {
        return tripId;
    }
    
}
