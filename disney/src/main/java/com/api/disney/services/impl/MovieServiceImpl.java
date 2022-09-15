package com.api.disney.services.impl;

import com.api.disney.dtos.MovieResponseDTO;
import com.api.disney.dtos.MovieRequestDTO;
import com.api.disney.mappers.MovieMapper;
import com.api.disney.models.Charac;
import com.api.disney.models.Movie;
import com.api.disney.repositories.CharacRepository;
import com.api.disney.repositories.MovieRepository;
import com.api.disney.services.CharacService;
import com.api.disney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private CharacRepository characRepository;

    @Autowired
    private CharacService characService;

    /*----C R U D----*/


    public MovieResponseDTO save(MovieRequestDTO movieDTO, boolean loadCharacters) {
        List<Charac> characs = characService.getAllById(movieDTO.getCharacters());
        Movie movie = movieMapper.movieRequestDTOToEntity(movieDTO);
        movie.setCharacters(characs);
        movieRepository.save(movie);
        return movieMapper.movieEntityToDTO(movie, loadCharacters);
    }


    public List<MovieResponseDTO> getAll(boolean loadCharacters) {
        return movieMapper.movieEntityListToDTOList(movieRepository.findAll(), loadCharacters);
    }

    @Override
    public void delete(Long id) {

    }

    public MovieResponseDTO update(Long id, MovieResponseDTO dto, boolean loadCharacters) {
//        Movie movie = movieRepository.findById(id).get();
//        movie.setTitle(dto.getTitle());
//        movie.setPicture(dto.getPicture());
//        movie.setGenres(dto.getGenres());
//        movie.setCharacters(dto.getCharacters());
//        movie.setScore(dto.getScore());
//        movie.setCreationDate(dto.getCreationDate());
//
//        return movieMapper.movieEntityToDTO(movieRepository.save(movie), loadCharacters);
        return null;
    }
    public MovieResponseDTO addCharacter(Long idMovie, List<Long> idCharacters, boolean loadCharacters) throws Exception {

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

}
