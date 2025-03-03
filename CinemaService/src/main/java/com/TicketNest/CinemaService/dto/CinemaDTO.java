package com.TicketNest.CinemaService.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CinemaDTO {
    private int cinemaId;
    @NotBlank(message = "Please provide cinema name")
    private String cinemaName;
    @NotBlank(message = "Please provide cinema location")
    private String location;
    @NotBlank(message = "Please provide cinema location pin code")
    private String pinCode;
    private boolean status;
}
