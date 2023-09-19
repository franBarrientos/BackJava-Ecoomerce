package com.treshermanitos.exceptions;

public class BadRequest extends RuntimeException {
    public BadRequest(String msg){
        super(msg);
    }
}
