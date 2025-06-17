package com.web2.trabalhoFinal.application.dto;

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    // Construtor para Sucesso
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Construtor para Erro
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null; 
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }


}
