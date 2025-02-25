package com.dataproject.yorha.validation.android;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AndroidExistsValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AndroidExists {
    String message() default "Android's ID not found.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

