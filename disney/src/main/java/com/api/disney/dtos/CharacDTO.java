package com.api.disney.dtos;

import com.api.disney.models.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacDTO {

    private Long id;
    private String picture;
    private String name;
    private Integer age;
    private Integer weight;
    private String story;
    private List<Movie> movies;

}
