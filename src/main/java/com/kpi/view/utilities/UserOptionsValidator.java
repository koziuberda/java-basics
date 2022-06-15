package com.kpi.view.utilities;

import com.kpi.model.exceptions.InvalidGPAValueException;
import com.kpi.view.exceptions.InvalidUserOptionException;

public class UserOptionsValidator {
    public static void validateUserOption(int option) throws InvalidUserOptionException {
        if (option < 1 || option > 5) {
            throw new InvalidUserOptionException();
        }
    }
}
