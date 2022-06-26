package com.api.disney.services;

import com.api.disney.dtos.GenreDTO;

import java.util.List;

public interface GenreService {

    GenreDTO save (GenreDTO dto);
    List<GenreDTO> getAll();
    GenreDTO update (Long id, GenreDTO dto);
    void delete (Long id);

}
