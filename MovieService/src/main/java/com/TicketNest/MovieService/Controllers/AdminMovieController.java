package com.TicketNest.MovieService.Controllers;

import com.TicketNest.MovieService.CustomException.NoRecordFoundException;
import com.TicketNest.MovieService.Entities.Movie;
import com.TicketNest.MovieService.Services.Movieservice;
import com.TicketNest.MovieService.dto.MovieDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie-service/admin")
public class AdminMovieController {

    @Autowired
    private Movieservice movieservice;

    @PostMapping("/movie")
    public ResponseEntity<?> addMovies(@Valid  @RequestBody MovieDTO movieDTO){
        MovieDTO savedMovieDTO = movieservice.addMovie(movieDTO);
        return new ResponseEntity<>(savedMovieDTO, HttpStatus.CREATED);
    }


    @PutMapping("/movie")
    public ResponseEntity<?> updateMovies(@Valid @RequestBody MovieDTO movieDTO){
        try{
            Movie movie = movieservice.getmovie(movieDTO.getMovieId());
            if(movie!=null){
                return new ResponseEntity(movieservice.updateMovie(movie, movieDTO), HttpStatus.CREATED);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (NoRecordFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<String> deleteMovies(@PathVariable Integer movieId){
        MovieDTO moviesDTO = movieservice.fetchMovie(movieId);
        if(moviesDTO!=null){
            movieservice.deleteMovie(moviesDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
