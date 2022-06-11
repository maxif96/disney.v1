package com.api.disney.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "The name can not be null.")
    @NotEmpty(message = "The name can not be empty.")
    private String name;
    @Column(nullable = false)
    @NotNull(message = "The age can not be null.")
    @NotEmpty(message = "The age can not be empty.")
    private Integer age;
    @Column
    private Integer weight;
    @Column(nullable = false)
    private String story;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable (name="characters_movies"
            , joinColumns = @JoinColumn(name="character_id")
            , inverseJoinColumns = @JoinColumn(name = "movie_id"))
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Movie> movies;

}