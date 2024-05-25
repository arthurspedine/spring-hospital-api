package com.hospital_api.domain;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg){
        super(msg);
    }
}
