package com.api.disney.services.impl;

import com.api.disney.dtos.MovieDTO;
import com.api.disney.mappers.MovieMapper;
import com.api.disney.models.Charac;
import com.api.disney.models.Movie;
import com.api.disney.repositories.CharacRepository;
import com.api.disney.repositories.MovieRepository;
import com.api.disney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieMapper movieMapper;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    CharacRepository characRepository;


    public MovieDTO save(MovieDTO movieDTO, boolean loadCharacters) {
        Movie movie = movieRepository.save(movieMapper.movieDTOToEntity(movieDTO));
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
        List<Charac> characList = new ArrayList<>();
        for (Long idCharacs : dto.getCharacters()) {
            characList.add(characRepository.findById(id).get());
        }
        movie.setCharacters(characList);
        movie.setScore(dto.getScore());
        movie.setCreationDate(dto.getCreationDate());

        return movieMapper.movieEntityToDTO(movieRepository.save(movie), loadCharacters);
    }

    public MovieDTO addCharacter(Long idMovie, List<Long> idCharacters, boolean loadCharacters) throws Exception {

        Movie movie = movieRepository
                .findById(idMovie)
                .orElseThrow(() -> new Exception("Movie not found"));

        List<Charac> charactersFromRequest = characRepository.findAllById(idCharacters);
        List<Charac> charactersFromMovie = movie.getCharacters();
        List<Charac> concatLists = Stream
                .concat(charactersFromMovie.stream(), charactersFromRequest.stream())
                .collect(Collectors.toList());

        movie.setCharacters(concatLists);
        movieRepository.save(movie);
        return movieMapper.movieEntityToDTO(movie, loadCharacters);
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }


}
