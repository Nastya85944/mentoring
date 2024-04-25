package com.example.demo.exception;

public class ErrorDetails {

    private final int statusCode;
    private final String errorMessage;

    public ErrorDetails(int statusCode, String errorMessage) {
        super();
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
