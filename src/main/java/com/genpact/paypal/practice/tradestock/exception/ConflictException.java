package com.genpact.paypal.practice.tradestock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException{
    public ConflictException(String message){
        super(message);
    }
    public ConflictException(String messsage, Throwable cause){
        super(messsage,cause);
    }
}
