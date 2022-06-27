package com.api.disney.services;

import com.api.disney.dtos.CharacDTO;
import com.api.disney.models.Charac;

import java.util.List;

public interface CharacService {

    CharacDTO save (CharacDTO dto, boolean loadMovies);
    List<CharacDTO> getAll (boolean loadMovies);
    CharacDTO update (Long id, CharacDTO dto, boolean loadMovies);
    void delete (Long id);
    List<Charac> getAllById(List<Long> id);




}
