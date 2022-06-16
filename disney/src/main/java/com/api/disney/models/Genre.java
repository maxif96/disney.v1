package com.api.disney.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column
    private String name;
    @Column
    private String picture;

    @ManyToOne(targetEntity = Movie.class)
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private List<Movie> movies;

}
