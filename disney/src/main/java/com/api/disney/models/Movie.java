package com.api.disney.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;
    @Column
    private String picture;
    @Column(nullable = false, unique = true)
    private String title;
    @Column(name = "creation_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
    @Column (nullable = false)
    private Integer score;
    @ManyToMany(mappedBy = "movies",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST})
    private List<Charac> characters;
    @OneToMany(mappedBy = "movies", fetch = FetchType.EAGER)
    private List<Genre> genre;
}