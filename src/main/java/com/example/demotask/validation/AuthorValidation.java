package com.example.demotask.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AuthorValidation implements ConstraintValidator<ValidAuthor, String> {

    @Override
    public void initialize(ValidAuthor constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        String[] words = value.trim().split("\\s+");
        if (words.length != 2) {
            return false;
        }
        for (String word : words) {
            if (!Character.isUpperCase(word.charAt(0))) {
                return false;
            }
        }
        return true;
    }
}