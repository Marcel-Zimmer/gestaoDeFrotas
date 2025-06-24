package com.web2.trabalhoFinal.domain.model.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessRuleException extends RuntimeException{
    // Construtor que aceita apenas uma mensagem de erro.
    public BusinessRuleException(String message) {
        super(message);
    }

    // Construtor que aceita uma mensagem e a causa original da exceção (para "envelopar" outros erros).
    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
