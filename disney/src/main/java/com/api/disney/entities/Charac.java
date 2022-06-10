package com.api.disney.entities;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Charac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id")
    private Long id;
    @Column
    private String picture;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private String story;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable (name="characters_movies"
            , joinColumns = @JoinColumn(name="character_id")
            , inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ToString.Exclude
    private List<Movie> movies;

}