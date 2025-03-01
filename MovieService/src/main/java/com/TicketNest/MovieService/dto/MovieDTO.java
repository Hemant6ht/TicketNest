package com.TicketNest.MovieService.dto;

import com.TicketNest.MovieService.customannotation.annotation.ValidDate;
import com.TicketNest.MovieService.customannotation.annotation.ValidDuration;
import com.TicketNest.MovieService.customannotation.annotation.ValidRating;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDTO {

    private int movieId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Genre is required")
    private String genre;

    @ValidDuration
    private String duration;

    @NotBlank(message = "Language is required")
    private String language;

    @ValidDate
    private String releaseDate;

    @NotBlank(message = "Rating is required")
    @ValidRating
    private String rating;
}
