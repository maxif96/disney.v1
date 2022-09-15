package com.api.disney.services;

import com.api.disney.dtos.GenreDTO;

import java.util.List;

public interface GenreService {

    /*----C R U D----*/

    /*----------------SAVE A GENRE-----------------(CREATE)*/
    GenreDTO save (GenreDTO dto);

    /*----------------GET ALL GENRES FROM DATABASE-----------------(READ)*/
    List<GenreDTO> getAll();

    /*----------------UPDATE A GENRE FROM DATABASE BY ID----------------(UPDATE)*/
    GenreDTO update (Long id, GenreDTO dto);

    /*----------------DELETE GENRE FROM DATABASE BY ID (SOFT)----------------(DELETE)*/
    void delete (Long id);

    /*----------------*/
}
