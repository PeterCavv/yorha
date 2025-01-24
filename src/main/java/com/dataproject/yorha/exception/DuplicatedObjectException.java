package com.dataproject.yorha.exception;

public class DuplicatedObjectException extends RuntimeException{

    public DuplicatedObjectException(String message){
        super(message);
    }

    public DuplicatedObjectException(String message, Throwable cause){
        super(message, cause);
    }
}
