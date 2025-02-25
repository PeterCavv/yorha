package com.dataproject.yorha.validation.appearance;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AppearanceExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AppearanceExists {
    String message() default "Status ID not found.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
