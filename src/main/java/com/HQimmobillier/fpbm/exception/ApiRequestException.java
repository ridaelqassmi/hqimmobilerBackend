package com.HQimmobillier.fpbm.exception;


import lombok.Data;
import org.springframework.http.HttpStatus;



@Data
public class ApiRequestException extends Throwable {

    private HttpStatus status;
    private String msg;
    public ApiRequestException(String message, HttpStatus status) {
        this.msg=message;
        this.status = status;
    }


}
