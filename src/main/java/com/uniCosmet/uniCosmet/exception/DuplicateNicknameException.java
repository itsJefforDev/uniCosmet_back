package com.uniCosmet.uniCosmet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Retornará código 409 Conflict
public class DuplicateNicknameException extends RuntimeException{
    public DuplicateNicknameException(String message) {
        super(message);
    }
}
