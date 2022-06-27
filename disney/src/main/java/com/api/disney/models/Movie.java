package com.api.disney.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity (name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE movies SET is_on = false WHERE movie_id =?")
@Where(clause = "is_on = true")
@Builder
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

    @Column(nullable = false)
    private Integer score;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "characters_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<Charac> characters;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Genre> genres;

    @Column(name = "is_on")
    private boolean isOn = Boolean.TRUE;
}