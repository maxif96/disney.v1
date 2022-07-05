package com.api.disney.mappers;

import com.api.disney.dtos.CharacBasicDTO;
import com.api.disney.dtos.MovieBasicDTO;
import com.api.disney.dtos.MovieDTO;
import com.api.disney.models.Charac;
import com.api.disney.models.Movie;
import com.api.disney.repositories.CharacRepository;
import com.api.disney.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired @Lazy
    CharacMapper characMapper;
    @Autowired
    CharacRepository characRepository;
    @Autowired
    MovieRepository movieRepository;

    public Movie movieDTOToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setPicture(movieDTO.getPicture());
        movie.setCreationDate(movieDTO.getCreationDate());
        movie.setScore(movieDTO.getScore());
        List<Charac> characs = new ArrayList<>();
        for (Long id: movieDTO.getCharacters()) {
            characs.add(characRepository.findById(id).get());
        }
        movie.setCharacters(characs);
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
        List<Long> idCharacList = new ArrayList<>();
        if (loadCharacters){
            for (Charac charac:
                  movie.getCharacters()) {
                idCharacList.add(charac.getId());
            }
            movieDTO.setCharacters(idCharacList);
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
