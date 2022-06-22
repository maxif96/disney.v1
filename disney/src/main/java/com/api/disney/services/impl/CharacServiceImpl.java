package com.api.disney.services.impl;

import com.api.disney.dtos.CharacDTO;
import com.api.disney.mappers.CharacMapper;
import com.api.disney.models.Charac;
import com.api.disney.repositories.CharacRepository;
import com.api.disney.services.CharacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacServiceImpl implements CharacService {

    @Autowired
    private CharacMapper characMapper;
    @Autowired
    private CharacRepository characRepository;

    public CharacDTO save(CharacDTO characDTO) {
        Charac charac = characMapper.CharacDTOToEntity(characDTO);
        Charac characSaved = characRepository.save(charac);
        CharacDTO characDTOSaved = characMapper.CharacEntityToDTO(characSaved);
        return characDTOSaved;
    }
}
