package com.api.disney.services.impl;

import com.api.disney.dtos.MovieDTO;
import com.api.disney.dtos.MovieRequestDTO;
import com.api.disney.mappers.MovieMapper;
import com.api.disney.models.Charac;
import com.api.disney.models.Movie;
import com.api.disney.repositories.MovieRepository;
import com.api.disney.services.CharacService;
import com.api.disney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacService characService;


    public MovieDTO save(MovieRequestDTO movieDTO, boolean loadCharacters) {
        List<Charac> characs = characService.getAllById(movieDTO.getCharacters());
        Movie movie = movieMapper.movieRequestDTOToEntity(movieDTO);
        movie.setCharacters(characs);
        movieRepository.save(movie);
        return movieMapper.movieEntityToDTO(movie, loadCharacters);
    }


    public List<MovieDTO> getAll(boolean loadCharacters) {
        return movieMapper.movieEntityListToDTOList(movieRepository.findAll(), loadCharacters);
    }

    public MovieDTO update(Long id, MovieDTO dto, boolean loadCharacters) {
        Movie movie = movieRepository.findById(id).get();
        movie.setTitle(dto.getTitle());
        movie.setPicture(dto.getPicture());
        movie.setGenres(dto.getGenres());
        movie.setCharacters(dto.getCharacters());
        movie.setScore(dto.getScore());
        movie.setCreationDate(dto.getCreationDate());

        return movieMapper.movieEntityToDTO(movieRepository.save(movie), loadCharacters);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }


}
