package com.example.moviecharactersapi.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "alias")
    private String alias;

    @Column(name = "gender")
    private String gender;

    @Column(name = "picture")
    private String pictureURL;

    @ManyToMany
    @JoinTable(
            name = "character_movies",
            joinColumns = {@JoinColumn(name = "character_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    public List<Character> characters;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
