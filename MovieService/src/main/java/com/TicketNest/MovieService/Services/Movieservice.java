package com.TicketNest.MovieService.Services;

import com.TicketNest.MovieService.CustomException.NoRecordFoundException;
import com.TicketNest.MovieService.Entities.Movie;
import com.TicketNest.MovieService.Repositories.MovieRepo;
import com.TicketNest.MovieService.dto.MovieDTO;
import com.TicketNest.MovieService.modelmapper.MovieModelMapper;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Movieservice {
    @Autowired
    private MovieRepo movieRepo;

    private ModelMapper modelMapper = MovieModelMapper.getmodelMapper();

    public MovieDTO convertToDTO(Movie movie){
        MovieDTO dto = modelMapper.map(movie, MovieDTO.class);
        return dto;
    }

    public Movie convertToEntity(MovieDTO movieDto){
        Movie entity = modelMapper.map(movieDto, Movie.class);
        return entity;
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepo.findAll().stream().map(movie->convertToDTO(movie)).collect(Collectors.toList());
    }

    public MovieDTO addMovie(MovieDTO movieDto) {
        return convertToDTO(movieRepo.save(convertToEntity(movieDto)));
    }

    public MovieDTO fetchMovie(Integer movieId) {
        return convertToDTO(movieRepo.findById(movieId).orElseThrow( () -> new NoRecordFoundException("No movie found")));
    }

    public MovieDTO updateMovie(Movie oldMovie,MovieDTO movieDto) {
        if(StringUtils.isNotBlank(movieDto.getTitle())){
            oldMovie.setTitle(movieDto.getTitle());
        }
        return convertToDTO(movieRepo.save(oldMovie));
    }

    public void deleteMovie(MovieDTO movieDTO) {
         movieRepo.delete(convertToEntity(movieDTO));
    }

    public boolean movieExist(int movieId) {
        return movieRepo.existsById(movieId);
    }

    public Movie getmovie(int movieId) {
        return movieRepo.findById(movieId).orElseThrow(() -> new NoRecordFoundException("No movie found"));
    }
}
