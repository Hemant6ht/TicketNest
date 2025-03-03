package com.TicketNest.CinemaService.Controller;

import com.TicketNest.CinemaService.dto.CinemaDTO;
import com.TicketNest.CinemaService.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("cinema-service/admin")
public class AdminController {

    @Autowired
    private CinemaService cinemaService;

    @DeleteMapping("/cinema/{cinemaId}")
    public ResponseEntity<String> deleteCinema(@PathVariable Integer cinemaId){
        CinemaDTO cinemaDTO = cinemaService.fetchCinema(cinemaId);
        if(cinemaDTO!=null){
            cinemaService.deleteCinema(cinemaDTO);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/cinema")
    public ResponseEntity<String> deleteAllCinema(){
        cinemaService.deleteAllCinema();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
