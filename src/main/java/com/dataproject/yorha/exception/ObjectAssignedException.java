package com.dataproject.yorha.exception;

public class ObjectAssignedException extends RuntimeException{

    public ObjectAssignedException(String message){
        super(message);
    }

    public ObjectAssignedException(String message, Throwable cause){
        super(message, cause);
    }
}
