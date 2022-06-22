package com.api.disney.mappers;

import com.api.disney.dtos.CharacDTO;
import com.api.disney.models.Charac;
import org.springframework.stereotype.Component;

@Component
public class CharacMapper {

    public Charac CharacDTOToEntity(CharacDTO characDTO) {
        Charac charac = new Charac();
        charac.setName(characDTO.getName());
        charac.setAge(characDTO.getAge());
        charac.setPicture(characDTO.getPicture());
        charac.setStory(characDTO.getStory());
        charac.setWeight(characDTO.getWeight());

        return charac;
    }

    public CharacDTO CharacEntityToDTO(Charac charac) {
        CharacDTO characDTO = new CharacDTO();
        characDTO.setId(charac.getId());
        characDTO.setName(charac.getName());
        characDTO.setAge(charac.getAge());
        characDTO.setPicture(charac.getPicture());
        characDTO.setStory(charac.getStory());
        characDTO.setWeight(charac.getWeight());

        return characDTO;
    }


}
