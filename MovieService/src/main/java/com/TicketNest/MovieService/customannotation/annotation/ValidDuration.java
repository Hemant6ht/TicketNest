package com.TicketNest.MovieService.customannotation.annotation;

import com.TicketNest.MovieService.customannotation.implementation.DateValidator;
import com.TicketNest.MovieService.customannotation.implementation.DurationValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DurationValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDuration {
    String message() default "Duration must a number with greater than  10 and less than 300";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    // Using static final constants for min and max values
    int min() default 10;
    int max() default 300;
}
