package com.api.disney.mappers;

import com.api.disney.dtos.CharacBasicDTO;
import com.api.disney.dtos.MovieBasicDTO;
import com.api.disney.dtos.MovieDTO;
import com.api.disney.models.Charac;
import com.api.disney.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired @Lazy
    CharacMapper characMapper;

    public Movie movieDTOToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setPicture(movieDTO.getPicture());
        movie.setCreationDate(movieDTO.getCreationDate());
        movie.setScore(movieDTO.getScore());
        movie.setCharacters(movieDTO.getCharacters());
        movie.setGenres(movieDTO.getGenres());

        return movie;
    }

    public MovieDTO movieEntityToDTO(Movie movie, boolean loadCharacters) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setPicture(movie.getPicture());
        movieDTO.setCreationDate(movie.getCreationDate());
        movieDTO.setScore(movie.getScore());
        movieDTO.setGenres(movie.getGenres());
        if (loadCharacters){
            characMapper.characEntityListToDTOList(movie.getCharacters(), loadCharacters);
            movieDTO.setCharacters(movie.getCharacters());
        }

        return movieDTO;
    }

    public List<MovieDTO> movieEntityListToDTOList(List<Movie> movieList, boolean loadCharacters) {
        List<MovieDTO> movieDTOList = new ArrayList<>();
        for (Movie movie : movieList) {
            movieDTOList.add(movieEntityToDTO(movie, loadCharacters));
        }
        return movieDTOList;
    }

    public List<MovieBasicDTO> movieEntityListToCharacBasicDTOList(List<Movie> movies){
        List<MovieBasicDTO> movieBasicDTOs = new ArrayList<>();
        MovieBasicDTO basicDTO;
        for (Movie movie : movies){
            basicDTO = new MovieBasicDTO();
            basicDTO.setId(movie.getId());
            basicDTO.setTitle(movie.getTitle());
            basicDTO.setPicture(movie.getPicture());
            movieBasicDTOs.add(basicDTO);
        }
        return movieBasicDTOs;
    }

}
