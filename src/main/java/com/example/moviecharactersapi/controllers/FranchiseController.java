package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping()
    public ResponseEntity <List<Franchise>> getAllFranchises() {
        List<Franchise> franchises = franchiseRepository.findAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(franchises, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getFranchise(@PathVariable int id){
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnFranchise = franchiseRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnFranchise, status);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<List<Movie>> getMoviesWithinFranchise(@PathVariable int id) {
        List<Movie> movieList = new ArrayList<>();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            movieList = franchiseRepository.findById(id).get().getMovies();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(movieList, status);
    }

    @GetMapping("/characters/{id}")
    public ResponseEntity<List<Character>> getCharactersWithinFranchise(@PathVariable int id) {
        List<Character> characterList = new ArrayList<>();
        List<Movie> movieList;
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            movieList = franchiseRepository.findById(id).get().getMovies();

            for (Movie movie: movieList) {
                characterList.addAll(movie.getCharacters());
            }
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(characterList, status);
    }

    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        Franchise add = franchiseRepository.save(franchise);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(add, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchiseById(@PathVariable int id) {
        Franchise franchise = franchiseRepository.getById(id);
        HttpStatus status;

        if (franchiseRepository.existsById(id)){
            for (Movie movie: franchise.getMovies()) {
                movie.setFranchise(null);
            }
            franchiseRepository.deleteById(id);
            status = HttpStatus.NO_CONTENT;

        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Franchise> updateFranchise(@PathVariable int id, @RequestBody Franchise franchise) {
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)){
            returnFranchise = franchiseRepository.save(franchise);
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(returnFranchise, status);
    }

    @PutMapping("{id}/update/movies")
    public ResponseEntity<Franchise> updateMovie(@PathVariable int id, @RequestBody int[] movies){
        Franchise returnFranchise = new Franchise();
        HttpStatus status;

        if (franchiseRepository.existsById(id)) {
            status = HttpStatus.OK;
            Franchise franchise = franchiseRepository.getById(id);
            List<Movie> temp = franchise.getMovies();

            for (int i = 0; i < movies.length; i++) {
                temp.add(movieRepository.getById(movies[i]));
                movieRepository.getById(movies[i]).setFranchise(franchiseRepository.getById(id));
            }
            franchise.setMovies(temp);
            returnFranchise = franchiseRepository.save(franchise);
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnFranchise, status);
    }
}
