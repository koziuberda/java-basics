package com.kpi.model.utilities;

import com.kpi.model.exceptions.InvalidGPAValueException;

public class GPAValidator {
    public static void validateGPA(double value) throws InvalidGPAValueException {
        if (value < 1 || value > 5) {
            throw new InvalidGPAValueException();
        }
    }
}
