package com.HQimmobillier.fpbm.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;


public class ApiRequestException extends RuntimeException {

    private HttpStatus status;
    public ApiRequestException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }


}
