package com.treshermanitos.exceptions;



public class NotFoundException extends RuntimeException {
    public NotFoundException(String msg){
        super(msg);
    }
}
