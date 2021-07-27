package com.example.moviecharactersapi.models;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "releaseYear")
    private String releaseYear;

    @Column(name = "director")
    private String director;

    @Column(name = "picture")
    private String pictureURL;

    @Column(name = "trailer")
    private String trailerURL;

    //@ManyToMany(mappedBy = "movies")
    //public List<Character> characters = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "character_movies",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    public List<Character> characters = new ArrayList<>();

    @JsonGetter("characters")
    public List <String> charactersGetter() {
        if (characters!= null) {
            return characters.stream()
                    .map(character -> {
                        return "/api/v1/characters/" + character.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @JsonGetter("franchise")
    public String franchise() {
        if (franchise != null) {
            return "/api/v1/franchises/" + franchise.getId();
        } else {
            return null;
        }
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getTrailerURL() {
        return trailerURL;
    }

    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }
}
