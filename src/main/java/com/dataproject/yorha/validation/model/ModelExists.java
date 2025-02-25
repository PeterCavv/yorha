package com.dataproject.yorha.validation.model;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ModelExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModelExists {
    String message() default "Model's ID not found.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
