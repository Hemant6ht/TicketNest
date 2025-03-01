package com.TicketNest.MovieService.modelmapper;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovieModelMapper {

    public static ModelMapper getmodelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        Converter<String, LocalDate> stringToLocalDate = ctx -> {
            if (ctx.getSource() == null || ctx.getSource().isEmpty()) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // Format as needed
            return LocalDate.parse(ctx.getSource(), formatter);
        };

        modelMapper.addConverter(stringToLocalDate, String.class, LocalDate.class);

        Converter<String, Integer> stringToInteger = ctx -> {
            if (ctx.getSource() == null || ctx.getSource().isEmpty()) {
                return null;
            }
            return Integer.valueOf(ctx.getSource());
        };

        modelMapper.addConverter(stringToInteger, String.class, Integer.class);

        Converter<String, BigDecimal> stringToBigDecimal = ctx -> {
            if (ctx.getSource() == null || ctx.getSource().isEmpty()) {
                return null;
            }
            return new BigDecimal(ctx.getSource());
        };

        modelMapper.addConverter(stringToBigDecimal, String.class, BigDecimal.class);

        return modelMapper;
    }
}
