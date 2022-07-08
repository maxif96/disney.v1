package com.api.disney.services;

import com.api.disney.dtos.MovieDTO;
import com.api.disney.models.Movie;

import java.util.List;

public interface MovieService {

    /*----C R U D----*/

    /*----------------SAVE A MOVIE-----------------(CREATE)*/
    MovieDTO save (MovieDTO movieDTO, boolean loadCharacters);
    /*----------------GET ALL MOVIES FROM DATABASE-----------------(READ)*/
    List<MovieDTO> getAll(boolean loadCharacters);
    /*----------------DELETE MOVIE FROM DATABASE BY ID (SOFT)----------------(DELETE)*/
    void delete (Long id);
    /*----------------UPDATE A MOVIE FROM DATABASE BY ID----------------(UPDATE)*/
    MovieDTO update (Long id, MovieDTO dto, boolean loadCharacters);

    /*----------------*/


    /*----------------ADD CHARACTER TO A MOVIE----------------*/
    MovieDTO addCharacter(Long idMovie, List<Long> idCharacters, boolean loadCharacters) throws Exception;



}
