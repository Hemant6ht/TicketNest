package com.TicketNest.CinemaService.service;

import com.TicketNest.CinemaService.Entity.Cinema;
import com.TicketNest.CinemaService.customexception.NoRecordFoundException;
import com.TicketNest.CinemaService.dto.CinemaDTO;
import com.TicketNest.CinemaService.repository.CinemaRepo;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaService {
    @Autowired
    private CinemaRepo cinemaRepo;

    private final ModelMapper modelMapper = new ModelMapper();

    public CinemaDTO convertToDTO(Cinema cinema){
        return modelMapper.map(cinema, CinemaDTO.class);
    }

    public Cinema convertToEntity(CinemaDTO cinemaDTO){
        return modelMapper.map(cinemaDTO, Cinema.class);
    }

    public List<CinemaDTO> getAllCinema() {
        return cinemaRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public CinemaDTO addCinema(CinemaDTO cinemaDTO) {
        return convertToDTO(cinemaRepo.save(convertToEntity(cinemaDTO)));
    }

    public CinemaDTO fetchCinema(Integer cinemaId) {
        return convertToDTO(cinemaRepo.findById(cinemaId).orElseThrow( () -> new NoRecordFoundException("No Cinema found")));
    }

    public CinemaDTO updateCinema(Cinema oldCinema,CinemaDTO cinemaDTO) {
        if(StringUtils.isNotBlank(cinemaDTO.getCinemaName())){
            oldCinema.setCinemaName(cinemaDTO.getCinemaName());
        }
        return convertToDTO(cinemaRepo.save(oldCinema));
    }

    public void deleteCinema(CinemaDTO cinemaDTO) {
        cinemaRepo.delete(convertToEntity(cinemaDTO));
    }

    public boolean CinemaExist(int cinemaId) {
        return cinemaRepo.existsById(cinemaId);
    }

    public Cinema getCinema(int cinemaId) {
        return cinemaRepo.findById(cinemaId).orElseThrow(() -> new NoRecordFoundException("No Cinema found"));
    }

    public void deleteAllCinema() {
        cinemaRepo.deleteAll();
    }
}
