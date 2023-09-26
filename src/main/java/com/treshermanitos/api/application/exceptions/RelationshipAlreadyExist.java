package com.treshermanitos.api.application.exceptions;

public class RelationshipAlreadyExist extends RuntimeException{
    public RelationshipAlreadyExist(String msg){
        super(msg);
    }
}
