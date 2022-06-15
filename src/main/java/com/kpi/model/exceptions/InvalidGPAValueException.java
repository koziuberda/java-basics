package com.kpi.model.exceptions;

public class InvalidGPAValueException extends Exception{

    public InvalidGPAValueException() {
        super("GPA value must be between 1 and 5 inclusive");
    }

    public InvalidGPAValueException(String message) {
        super(message);
    }
}
