package com.treshermanitos.api.application.exceptions;

public class Unathorized extends RuntimeException{
    public Unathorized(String message) {
        super(message);
    }
}
