package com.treshermanitos.api.application.exceptions;

public class BadRequest extends RuntimeException {
    public BadRequest(String msg){
        super(msg);
    }
}
