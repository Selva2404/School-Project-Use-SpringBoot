package org.easyschool.Annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.easyschool.Validater.PasswordvalidValidator;
import org.springframework.lang.Contract;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordvalidValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Passwordvalid {

    String message() default "Password not Strong";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
