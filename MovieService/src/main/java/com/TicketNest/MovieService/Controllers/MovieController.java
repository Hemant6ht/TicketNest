package com.TicketNest.MovieService.Controllers;

import com.TicketNest.MovieService.CustomException.NoRecordFoundException;
import com.TicketNest.MovieService.Entities.Movie;
import com.TicketNest.MovieService.Services.Movieservice;
import com.TicketNest.MovieService.dto.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-service")
public class MovieController {

    @Autowired
    private Movieservice movieservice;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<Object> fetchMovie(@PathVariable Integer movieId){
        try {
            MovieDTO movieDTO = movieservice.fetchMovie(movieId);
            return new ResponseEntity<>(movieDTO,HttpStatus.OK);
        }catch(NoRecordFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movie")
    public ResponseEntity<Object> getAllMovies(){
        List<MovieDTO> moviesDTO = movieservice.getAllMovies();
        if(!moviesDTO.isEmpty()){
            return new ResponseEntity<>(moviesDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>("No movie found",HttpStatus.NO_CONTENT);
    }
}
