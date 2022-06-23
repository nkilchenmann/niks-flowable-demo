package com.example.flowabledemo;

public enum ErrorCodes {
    ERROR_88888("88888", "Error Code: 88888 - Mocked Error");

    private final String errorCode;
    private final String errorMessage;

    ErrorCodes(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
