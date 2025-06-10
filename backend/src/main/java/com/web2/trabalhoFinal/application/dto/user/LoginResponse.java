package com.web2.trabalhoFinal.application.dto.user;

public class LoginResponse {
    private boolean success;
    private String message;
    private Long userId;
    private boolean isSuperUser;

    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponse(boolean success, String message, Long userId, boolean isSuperUser) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.isSuperUser = isSuperUser;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getUserId() {
        return userId;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }    

}
