package com.automation.core.exceptions;

public class TestDataException extends RuntimeException {

    public TestDataException(String message) {
        super(message);
    }

    public TestDataException(String message, Throwable cause) {
        super(message, cause);
    }
}