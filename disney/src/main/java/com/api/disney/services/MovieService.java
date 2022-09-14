package com.api.disney.services;

import com.api.disney.dtos.MovieResponseDTO;
import com.api.disney.dtos.MovieRequestDTO;

import java.util.List;

public interface MovieService {

    MovieResponseDTO save (MovieRequestDTO movieDTO, boolean loadCharacters);

    List<MovieResponseDTO> getAll(boolean loadCharacters);

    void delete (Long id);

    MovieResponseDTO update (Long id, MovieResponseDTO dto, boolean loadCharacters);



}
