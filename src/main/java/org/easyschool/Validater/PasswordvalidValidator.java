package org.easyschool.Validater;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.easyschool.Annotation.Passwordvalid;

import java.util.Arrays;
import java.util.List;

public class PasswordvalidValidator implements ConstraintValidator<Passwordvalid, String> {

    List<String> passwords;
    @Override
    public void initialize(Passwordvalid constraintAnnotation) {
        passwords= Arrays.asList("123456", "password", "123456789", "12345678", "12345", "1234567", "qwerty", "abc123", "football", "monkey");

    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !passwords.contains(value);
    }
}
