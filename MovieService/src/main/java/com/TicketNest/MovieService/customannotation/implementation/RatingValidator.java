package com.TicketNest.MovieService.customannotation.implementation;

import com.TicketNest.MovieService.customannotation.annotation.ValidDuration;
import com.TicketNest.MovieService.customannotation.annotation.ValidRating;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class RatingValidator implements ConstraintValidator<ValidRating,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        try {
            BigDecimal r = new BigDecimal(value);
            return r.compareTo(BigDecimal.ZERO) >= 0 && r.compareTo(BigDecimal.TEN) <= 0;
        }catch(NumberFormatException e){
            return false;
        }

    }
}
