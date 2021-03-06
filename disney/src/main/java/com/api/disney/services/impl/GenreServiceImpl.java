package com.api.disney.services.impl;

import com.api.disney.dtos.GenreDTO;
import com.api.disney.mappers.GenreMapper;
import com.api.disney.models.Genre;
import com.api.disney.repositories.GenreRepository;
import com.api.disney.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    GenreMapper genreMapper;

    /*----C R U D----*/

    /*----------------SAVE A GENRE-----------------(CREATE)*/
    public GenreDTO save(GenreDTO dto) {
        Genre genre = genreRepository.save(genreMapper.genreDTOToEntity(dto));
        return genreMapper.genreEntityToDTO(genre);
    }

    /*----------------GET ALL GENRES FROM DATABASE-----------------(READ)*/
    public List<GenreDTO> getAll() {
        return genreMapper.genreEntityListToDTOList(genreRepository.findAll());
    }

    /*----------------UPDATE A GENRE FROM DATABASE BY ID----------------(UPDATE)*/
    public GenreDTO update(Long id, GenreDTO dto) {
        Genre genre = genreRepository.findById(id).get();
        genre.setName(dto.getName());
        genre.setPicture(dto.getPicture());

        return genreMapper.genreEntityToDTO(genreRepository.save(genre));
    }

    /*----------------DELETE GENRE FROM DATABASE BY ID (SOFT)----------------(DELETE)*/
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

    /*----------------*/
}
