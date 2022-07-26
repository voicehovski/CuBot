package com.goit.cubot.exceptions;

public class UnsupportedCurrencyException extends RuntimeException {
    private String currencyCode;

    public UnsupportedCurrencyException(String message, String currencyCode) {
        super(message);
        this.currencyCode = currencyCode;
    }
}
