package com.treshermanitos.exceptions;

public class RelationshipAlreadyExist extends RuntimeException{
    public RelationshipAlreadyExist(String msg){
        super(msg);
    }
}
