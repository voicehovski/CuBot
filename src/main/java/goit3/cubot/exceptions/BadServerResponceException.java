package com.goit.cubot.exceptions;

public class BadServerResponceException extends RuntimeException {
    private String responceCode;

    public BadServerResponceException(String message, String responceCode) {
        super(message);
        this.responceCode = responceCode;
    }
}
