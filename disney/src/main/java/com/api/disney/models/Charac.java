package com.api.disney.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity (name = "characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE characters SET is_on = false WHERE character_id =?")
@Where(clause = "is_on = true")
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

    @Column
    private Integer weight;

    @Column(nullable = false)
    private String story;

    @ManyToMany(mappedBy = "characters",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST})
    @ToString.Exclude
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Movie> movies;

    @Column(name = "is_on")
    private boolean isOn = Boolean.TRUE;

}