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

    public CharacDTO save(CharacDTO characDTO, boolean loadMovies) {
        Charac charac = characRepository.save(characMapper.characDTOToEntity(characDTO));
        return characMapper.characEntityToDTO(charac, loadMovies);
    }

    public List<CharacDTO> getAll(boolean loadMovies) {
        return characMapper.characEntityListToDTOList(characRepository.findAll(), loadMovies);
    }

    public void delete(Long id) {
        characRepository.deleteById(id);
    }
    public List<Charac> getAllById(List<Long> id) {

        List<Charac> characs = characRepository.findAllById(id);
        return characs;
    }

    public CharacDTO update(Long id, CharacDTO dto, boolean loadMovies) {
        Charac charac = characRepository.findById(id).get();
        charac.setAge(dto.getAge());
        charac.setName(dto.getName());
        charac.setPicture(dto.getPicture());
        charac.setStory(dto.getStory());
        charac.setWeight(dto.getWeight());
        return characMapper.characEntityToDTO(characRepository.save(charac), loadMovies);
    }


}
