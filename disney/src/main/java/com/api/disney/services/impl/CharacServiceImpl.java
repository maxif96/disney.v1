package com.api.disney.services.impl;

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

    public CharacDTO save(CharacDTO characDTO) {
        Charac charac = characMapper.characDTOToEntity(characDTO);
        Charac characSaved = characRepository.save(charac);
        CharacDTO characDTOSaved = characMapper.characEntityToDTO(characSaved);
        return characDTOSaved;
    }

    public List<CharacDTO> getAll() {
        List<Charac> characs = characRepository.findAll();
        List<CharacDTO> characDTOList = characMapper.characEntityListToDTOList(characs);
        return characDTOList;
    }
}
