package com.TicketNest.CinemaService.Controller;

import com.TicketNest.CinemaService.customexception.NoRecordFoundException;
import com.TicketNest.CinemaService.dto.CinemaDTO;
import com.TicketNest.CinemaService.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cinema-service")
public class CinemaController {
    @Autowired
    private CinemaService cinemaService;

    @GetMapping("/cinema/{cinemaId}")
    public ResponseEntity<?> fetchCinema(@PathVariable Integer cinemaId){
        try {
            CinemaDTO cinemaDTO = cinemaService.fetchCinema(cinemaId);
            return new ResponseEntity<>(cinemaDTO, HttpStatus.OK);
        }catch(NoRecordFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cinema")
    public ResponseEntity<?> getAllCinemas(){
        List<CinemaDTO> cinemaDTOS = cinemaService.getAllCinema();
        if(!cinemaDTOS.isEmpty()){
            return new ResponseEntity<>(cinemaDTOS, HttpStatus.OK);
        }
        return new ResponseEntity<>("No Cinema found",HttpStatus.NO_CONTENT);
    }
}
