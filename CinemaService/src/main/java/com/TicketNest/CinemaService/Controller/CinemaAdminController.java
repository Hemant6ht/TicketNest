package com.TicketNest.CinemaService.Controller;

import com.TicketNest.CinemaService.Entity.Cinema;
import com.TicketNest.CinemaService.customexception.NoRecordFoundException;
import com.TicketNest.CinemaService.dto.CinemaDTO;
import com.TicketNest.CinemaService.service.CinemaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/cinema-service/cinema-admin")
public class CinemaAdminController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/cinema")
    public ResponseEntity<?> addCinema(@Valid @RequestBody CinemaDTO cinemaDTO){
        CinemaDTO savedcinemaDTO = cinemaService.addCinema(cinemaDTO);
        return new ResponseEntity<>(savedcinemaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/cinema")
    public ResponseEntity<?> updateCinema(@Valid @RequestBody CinemaDTO cinemaDTO){
        try{
            Cinema cinema = cinemaService.getCinema(cinemaDTO.getCinemaId());
            if(cinema!=null){
                return new ResponseEntity(cinemaService.updateCinema(cinema, cinemaDTO), HttpStatus.CREATED);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (NoRecordFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
