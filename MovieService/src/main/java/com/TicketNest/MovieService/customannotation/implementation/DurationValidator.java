package com.TicketNest.MovieService.customannotation.implementation;

import com.TicketNest.MovieService.customannotation.annotation.ValidDate;
import com.TicketNest.MovieService.customannotation.annotation.ValidDuration;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DurationValidator implements ConstraintValidator<ValidDuration,String> {

    private int min;
    private int max;

    @Override
    public void initialize(ValidDuration constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            int t = Integer.valueOf(value);
            if(t<min || t>max){
                return false;
            }
            return true;
        }catch(NumberFormatException e){
            return false;
        }

    }
}
