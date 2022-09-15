package com.api.disney.services.impl;

import com.api.disney.dtos.CharacBasicDTO;
import com.api.disney.dtos.CharacDTO;
import com.api.disney.mappers.CharacMapper;
import com.api.disney.models.Charac;
import com.api.disney.repositories.CharacRepository;
import com.api.disney.services.CharacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacServiceImpl implements CharacService {

    @Autowired
    private CharacMapper characMapper;
    @Autowired
    private CharacRepository characRepository;

    /*----C R U D----*/

    /*----------------SAVE A CHARACTER-----------------(CREATE)*/
    public CharacDTO save(CharacDTO characDTO, boolean loadMovies) {
        Charac charac = characRepository.save(characMapper.characDTOToEntity(characDTO));
        return characMapper.characEntityToDTO(charac, loadMovies);
    }
    /*----------------GET ALL CHARACTERS FROM DATABASE-----------------(READ)*/
    public List<CharacDTO> getAll(boolean loadMovies) {
        return characMapper.characEntityListToDTOList(characRepository.findAll(), loadMovies);
    }
    /*----------------UPDATE CHARACTER FROM DATABASE BY ID----------------(UPDATE)*/
    public CharacDTO update(Long id, CharacDTO dto, boolean loadMovies) {
        Charac charac = characRepository.findById(id).get();
        charac.setAge(dto.getAge());
        charac.setName(dto.getName());
        charac.setPicture(dto.getPicture());
        charac.setStory(dto.getStory());
        charac.setWeight(dto.getWeight());
        return characMapper.characEntityToDTO(characRepository.save(charac), loadMovies);
    }
    /*----------------DELETE CHARACTER FROM DATABASE BY ID (SOFT)----------------(DELETE)*/
    public void delete(Long id) {
        characRepository.deleteById(id);
    }

    @Override
    public List<Charac> getAllById(List<Long> characters) {
        return null;
    }

    /*----------------*/



}
