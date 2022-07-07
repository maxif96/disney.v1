package com.api.disney.services;

import com.api.disney.dtos.MovieDTO;
import com.api.disney.models.Movie;

import java.util.List;

public interface MovieService {

    MovieDTO save (MovieDTO movieDTO, boolean loadCharacters);

    List<MovieDTO> getAll(boolean loadCharacters);

    void delete (Long id);

    MovieDTO update (Long id, MovieDTO dto, boolean loadCharacters);

    MovieDTO addCharacter(Long idMovie, List<Long> idCharacters, boolean loadCharacters) throws Exception;



}
