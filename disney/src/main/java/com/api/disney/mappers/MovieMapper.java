package com.api.disney.mappers;

import com.api.disney.dtos.MovieBasicDTO;
import com.api.disney.dtos.MovieResponseDTO;
import com.api.disney.dtos.MovieRequestDTO;
import com.api.disney.models.Genre;
import com.api.disney.models.Movie;
import com.api.disney.repositories.CharacRepository;
import com.api.disney.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    @Autowired
    @Lazy
    private CharacMapper characMapper;
    @Autowired
    private CharacRepository characRepository;

    @Autowired
    private MovieRepository movieRepository;

    public Movie movieDTOToEntity(MovieRequestDTO movieResponseDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieResponseDTO.getTitle());
        movie.setPicture(movieResponseDTO.getPicture());
        movie.setCreationDate(movieResponseDTO.getCreationDate());
        movie.setScore(movieResponseDTO.getScore());

        return movie;
    }

    public MovieResponseDTO movieEntityToDTO(Movie movie, boolean loadCharacters) {
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        movieResponseDTO.setId(movie.getId());
        movieResponseDTO.setTitle(movie.getTitle());
        movieResponseDTO.setPicture(movie.getPicture());
        movieResponseDTO.setCreationDate(movie.getCreationDate());
        movieResponseDTO.setScore(movie.getScore());
        movieResponseDTO.setGenres(movie
                .getGenres().stream()
                .map(Genre::getId).
                collect(Collectors.toList()));
        if (loadCharacters){
            movieResponseDTO.setCharacters(movie.getGenres().stream()
                    .map(Genre::getId).
                    collect(Collectors.toList()));
        }

        return movieResponseDTO;
    }

    public List<MovieResponseDTO> movieEntityListToDTOList(List<Movie> movieList, boolean loadCharacters) {
        List<MovieResponseDTO> movieResponseDTOList = new ArrayList<>();
        for (Movie movie : movieList) {
            movieResponseDTOList.add(movieEntityToDTO(movie, loadCharacters));
        }
        return movieResponseDTOList;
    }

    public List<MovieBasicDTO> movieEntityListToCharacBasicDTOList(List<Movie> movies) {
        List<MovieBasicDTO> movieBasicDTOs = new ArrayList<>();
        MovieBasicDTO basicDTO;
        for (Movie movie : movies) {
            basicDTO = new MovieBasicDTO();
            basicDTO.setId(movie.getId());
            basicDTO.setTitle(movie.getTitle());
            basicDTO.setPicture(movie.getPicture());
            movieBasicDTOs.add(basicDTO);
        }
        return movieBasicDTOs;
    }

    public Movie movieRequestDTOToEntity(MovieRequestDTO movieDTO) {
        return null;
    }
}
