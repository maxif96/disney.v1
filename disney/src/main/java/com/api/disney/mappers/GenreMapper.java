package com.api.disney.mappers;

import com.api.disney.dtos.GenreDTO;
import com.api.disney.models.Genre;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {


    public Genre genreDTOToEntity(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setName(genreDTO.getName());
        genre.setPicture(genreDTO.getPicture());

        return genre;
    }


    public GenreDTO genreEntityToDTO(Genre genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        genreDTO.setPicture(genre.getPicture());

        return genreDTO;
    }

    public List<GenreDTO> genreEntityListToDTOList(List<Genre> genreList){
        List<GenreDTO> genreDTOList = new ArrayList<>();
        for (Genre genre : genreList){
            genreDTOList.add(genreEntityToDTO(genre));
        }
        return genreDTOList;
    }

}
