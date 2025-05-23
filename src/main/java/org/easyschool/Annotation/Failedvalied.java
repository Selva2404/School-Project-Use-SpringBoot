package org.easyschool.Annotation;

import jakarta.validation.Constraint;

import jakarta.validation.Payload;
import org.easyschool.Validater.FailedvaliedValidator;

import java.lang.annotation.*;


import static java.lang.annotation.ElementType.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = FailedvaliedValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RUNTIME)
public @interface Failedvalied {
    String message() default "failed are not valid";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String field();
    String fieldMatch();


    @Target({TYPE})
    @Retention(RUNTIME)
    public @interface List {
        Failedvalied[] value();
    }
}
