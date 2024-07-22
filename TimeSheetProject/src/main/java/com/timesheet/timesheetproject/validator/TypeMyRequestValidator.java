package com.timesheet.timesheetproject.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class TypeMyRequestValidator implements ConstraintValidator<ValidTypeMyRequestOff, String> {

    private final List<String> validTypes = Arrays.asList("Full day", "Morning", "Afternoon");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && validTypes.contains(value);
    }
}
