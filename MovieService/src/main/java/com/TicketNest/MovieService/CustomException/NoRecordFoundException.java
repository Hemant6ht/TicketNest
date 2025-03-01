package com.TicketNest.MovieService.CustomException;

public class NoRecordFoundException extends RuntimeException{
    public NoRecordFoundException(String message) {
        super(message);
    }
}
