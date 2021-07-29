package com.example.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a franchise with details such as name and description.
 * The entity represent the tables in the database for a franchise.
 */
@Entity
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    /**
     * One franchise can have several movies, hence a link is created to the Movie entity.
     */
    @OneToMany(mappedBy = "franchise")
    List<Movie> movies;

    /**
     * @return : Returns a List with Strings representing movie URLs.
     */
    @JsonGetter("movies")
    public List<String> movies() {
        if (movies != null) {
            return movies.stream()
                    .map(movie -> {
                        return "/api/v1/movies/" + movie.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
