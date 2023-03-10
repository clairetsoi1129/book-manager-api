package com.techreturners.bookmanager.exception;

public class ErrorType {

    private final String message;
    private final String code;
    private final String error;
    private final String classType;

    public ErrorType(String message, String code, String error, String classType) {
        this.message = message;
        this.code = code;
        this.error = error;
        this.classType = classType;
    }
}
