package com.api.disney.mappers;

import com.api.disney.dtos.CharacBasicDTO;
import com.api.disney.dtos.CharacDTO;
import com.api.disney.models.Charac;
import com.api.disney.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacMapper {

    @Autowired
    MovieMapper movieMapper;

    public Charac characDTOToEntity(CharacDTO characDTO) {
        Charac charac = new Charac();
        charac.setName(characDTO.getName());
        charac.setAge(characDTO.getAge());
        charac.setPicture(characDTO.getPicture());
        charac.setStory(characDTO.getStory());
        charac.setWeight(characDTO.getWeight());
        List<Movie> movieList = new ArrayList<>();
//        if (characDTO.getMovies() != null) {
//            for (MovieDTO movieDTO : characDTO.getMovies()) {
//                movieList.add(movieMapper.movieDTOToEntity(movieDTO));
//            }
//            charac.setMovies(movieList);
//        }

        return charac;
    }

    public CharacDTO characEntityToDTO(Charac charac, boolean loadMovies) {
        CharacDTO characDTO = new CharacDTO();
        characDTO.setId(charac.getId());
        characDTO.setName(charac.getName());
        characDTO.setAge(charac.getAge());
        characDTO.setPicture(charac.getPicture());
        characDTO.setStory(charac.getStory());
        characDTO.setWeight(charac.getWeight());
//        if (loadMovies) {
//            List<MovieDTO> movieDTOList = new ArrayList<>();
//            movieDTOList = movieMapper.movieEntityListToDTOList(charac.getMovies(), false);
//            characDTO.setMovies(movieDTOList);
//        }


        return characDTO;
    }

    public List<CharacDTO> characEntityListToDTOList(List<Charac> characs, boolean loadMovies) {
        List<CharacDTO> characDTOList = new ArrayList<>();
        for (Charac charac : characs) {
            characDTOList.add(characEntityToDTO(charac, loadMovies));
        }
        return characDTOList;
    }

    public List<CharacBasicDTO> characEntityListToCharacBasicDTOList(List<Charac> characs) {
        List<CharacBasicDTO> characBasicDTOs = new ArrayList<>();
        CharacBasicDTO basicDTO;
        for (Charac charac : characs) {
            basicDTO = new CharacBasicDTO();
            basicDTO.setId(charac.getId());
            basicDTO.setName(charac.getName());
            basicDTO.setPicture(charac.getPicture());
            characBasicDTOs.add(basicDTO);
        }
        return characBasicDTOs;
    }


}
