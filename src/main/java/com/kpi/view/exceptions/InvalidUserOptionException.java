package com.kpi.view.exceptions;

public class InvalidUserOptionException extends Exception {
    public InvalidUserOptionException() {
        super("Invalid option was selected. Please, try again.");
    }

    public InvalidUserOptionException(String message) {
        super(message);
    }
}
