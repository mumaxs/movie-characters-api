package com.example.moviecharactersapi.models;


import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a movie with details such as title, genre, release year, director, picture and trailer.
 * The entity represent the tables in the database for a movie.
 */
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    /**
     * A movie can be related to several characters, hence there is a many to many relationship to the entity Character.
     * A joining table is created within Movie and Character with following foreign keys representing movieIds and characterIds.
     */
    @ManyToMany
    @JoinTable(
            name = "character_movies",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    public List<Character> characters = new ArrayList<>();

    /**
     * @return : Returns a List with Strings representing character URLs.
     */
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

    /**
     * Several movies can be related to a single franchise, hence a link is created to the Franchise entity.
     */
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    /**
     * @return : Returns a List with Strings representing franchise URLs.
     */
    @JsonGetter("franchise")
    public String franchise() {
        if (franchise != null) {
            return "/api/v1/franchises/" + franchise.getId();
        } else {
            return null;
        }
    }

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
