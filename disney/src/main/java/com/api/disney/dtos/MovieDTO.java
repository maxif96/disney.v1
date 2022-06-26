package com.api.disney.dtos;

import com.api.disney.models.Charac;
import com.api.disney.models.Genre;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MovieDTO {

    private Long id;
    private String picture;
    private String title;
    private LocalDate creationDate;
    private Integer score;
    private List<Charac> characters;
    private List<Genre> genres;

}
