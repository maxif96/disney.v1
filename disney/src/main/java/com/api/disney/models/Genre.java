package com.api.disney.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity (name = "genres")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE genres SET is_on = false WHERE genre_id =?")
@Where(clause = "is_on = true")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long id;

    @Column
    private String name;
    @Column
    private String picture;
    @Column(name = "is_on")
    private boolean isOn = Boolean.TRUE;

//    @ManyToOne(targetEntity = Movie.class)
//    @JoinColumn(name = "movie_id")
//    @ToString.Exclude
//    private List<Movie> movies;

}
