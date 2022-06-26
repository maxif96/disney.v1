package com.api.disney.services;

import com.api.disney.dtos.MovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO save (MovieDTO movieDTO, boolean loadCharacters);

    List<MovieDTO> getAll(boolean loadCharacters);

    void delete (Long id);

    MovieDTO update (Long id, MovieDTO dto, boolean loadCharacters);



}
