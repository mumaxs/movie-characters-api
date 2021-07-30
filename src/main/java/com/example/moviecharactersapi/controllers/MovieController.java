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

    /**
     * Adding a movie to the database.
     * @param movie : Movie.
     * @return : New movie object.
     */
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie returnMovie = movieRepository.save(movie);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnMovie, status);
    }

    /**
     * Fetching all the current movies from the database.
     * @return : Returns a List of movies.
     */
    @GetMapping()
    public ResponseEntity <List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        HttpStatus resp = HttpStatus.OK;
        return new ResponseEntity<>(movies, resp);
    }

    /**
     * Fetch a movie from the database with a given id.
     * @param id : Id identifier.
     * @return : Returns the movie from given id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable int id){
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnMovie = movieRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnMovie, status);
    }

    /**
     * Fetching characters within a specific movie.
     * @param id : Given id for a specific movie.
     * @return : Returns a List of characters within the specific movie.
     */
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
     * Updates a movie in the database.
     * @param id : Given id for a specific movie.
     * @param movie : Movie object with the updates.
     * @return : Returns an updated movie object.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.NO_CONTENT;
            returnMovie = movieRepository.save(movie);
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(returnMovie, status);
    }

    /**
     * Updating characters which are in a movie.
     * @param id : Given id for a specific character.
     * @param character : Array of characters.
     * @return : Returns movie with updated characters.
     */
    @PutMapping("{id}/update/characters")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id, @RequestBody int[] character){
        Movie returnMovie = new Movie();
        HttpStatus status;

        if (movieRepository.existsById(id)) {
            status = HttpStatus.NO_CONTENT;
            Movie movie = movieRepository.getById(id);

            List<Character> temp = movie.getCharacters();
            for (int i = 0; i < character.length; i++) {
                temp.add(characterRepository.getById(character[i]));
            }
            movie.setCharacters(temp);
            returnMovie = movieRepository.save(movie);

        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(returnMovie, status);
    }

    /**
     * Deleting a movie from the database.
     * @param id : Given id for a specific movie.
     * @return : Returns HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovieById(@PathVariable int id) {
        HttpStatus status;
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }
}
