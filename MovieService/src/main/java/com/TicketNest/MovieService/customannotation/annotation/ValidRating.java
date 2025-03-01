package com.TicketNest.MovieService.customannotation.annotation;

import com.TicketNest.MovieService.customannotation.implementation.DurationValidator;
import com.TicketNest.MovieService.customannotation.implementation.RatingValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

@Constraint(validatedBy = RatingValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRating {
    String message() default "Rating must be in between 0-10";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
