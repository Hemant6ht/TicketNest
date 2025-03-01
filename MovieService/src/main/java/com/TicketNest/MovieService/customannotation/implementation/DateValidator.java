package com.TicketNest.MovieService.customannotation.implementation;

import com.TicketNest.MovieService.customannotation.annotation.ValidDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<ValidDate,String> {
    private String datePattern;
    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.datePattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
            LocalDate date = LocalDate.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
