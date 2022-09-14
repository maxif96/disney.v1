package com.api.disney.models;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id =?")
@Where(clause = "deleted = false")
public class Charac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String picture;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column
    private Integer weight;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String story;

    @ManyToMany(mappedBy = "characters",
            cascade = {CascadeType.MERGE,
                    CascadeType.PERSIST})
    private List<Movie> movies;

    @Column
    private boolean deleted;

}