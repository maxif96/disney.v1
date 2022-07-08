package com.api.disney.services;

import com.api.disney.dtos.CharacDTO;

import java.util.List;

public interface CharacService {

    /*----C R U D----*/

    /*----------------SAVE A CHARACTER-----------------(CREATE)*/
    CharacDTO save (CharacDTO dto, boolean loadMovies);
    /*----------------GET ALL CHARACTERS FROM DATABASE-----------------(READ)*/
    List<CharacDTO> getAll (boolean loadMovies);
    /*----------------UPDATE CHARACTER FROM DATABASE BY ID----------------(UPDATE)*/
    CharacDTO update (Long id, CharacDTO dto, boolean loadMovies);
    /*----------------DELETE CHARACTER FROM DATABASE BY ID (SOFT)----------------(DELETE)*/
    void delete (Long id);

    /*-----------------*/


}
