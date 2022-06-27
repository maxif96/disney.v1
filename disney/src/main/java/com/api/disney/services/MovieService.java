package com.api.disney.services;

import com.api.disney.dtos.MovieDTO;
import com.api.disney.dtos.MovieRequestDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save (MovieRequestDTO movieDTO, boolean loadCharacters);

    List<MovieDTO> getAll(boolean loadCharacters);

    void delete (Long id);

    MovieDTO update (Long id, MovieDTO dto, boolean loadCharacters);



}
