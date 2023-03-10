package com.techreturners.bookmanager.exception;

public class ErrorType {

    private String message;
    private String code;
    private String error;
    private String classType;

    public ErrorType(String message, String code, String error, String classType) {
        this.message = message;
        this.code = code;
        this.error = error;
        this.classType = classType;
    }


}

