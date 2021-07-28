package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie returnMovie = movieRepository.save(movie);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnMovie, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnMovie = movieRepository.save(movie);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnMovie, status);
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<List<Character>> getCharactersWithinMovie(@PathVariable int id) {
        List<Character> characterList = new ArrayList<>();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            characterList = movieRepository.findById(id).get().getCharacters();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(characterList, status);
    }

    /**
     * Updating characters in a movie
     * @param id : Integer
     * @param character : Character
     * @return : ResponseEntity
     */
    @PutMapping("{id}/update/characters")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody int[] character){
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            Movie movie = movieRepository.getById(id);

            List<Character> temp = movie.getCharacters();
            for (int i = 0; i < character.length; i++) {
                temp.add(characterRepository.getById(character[i]));
            }
            movie.setCharacters(temp);
            returnMovie = movieRepository.save(movie);

        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnMovie, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovieById(@PathVariable int id) {
        Movie movie = movieRepository.getById(id);
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }
}
