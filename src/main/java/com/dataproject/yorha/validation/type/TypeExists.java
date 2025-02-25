package com.dataproject.yorha.validation.type;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TypeExistsValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TypeExists {
    String message() default "Type's ID not found.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
