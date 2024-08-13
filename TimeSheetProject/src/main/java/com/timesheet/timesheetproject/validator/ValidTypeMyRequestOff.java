package com.timesheet.timesheetproject.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TypeMyRequestValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTypeMyRequestOff {
    String message() default "Invalid type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
