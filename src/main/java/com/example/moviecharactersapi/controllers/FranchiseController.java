package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @GetMapping()
    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Franchise> addFranchise(@RequestBody Franchise franchise) {
        Franchise add = franchiseRepository.save(franchise);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(add, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Franchise> deleteFranchise(@PathVariable long id, @RequestBody Franchise franchise) {
        for (Movie m: franchise.getMovies()) {
            franchise.setMovies(null);
        }
        franchiseRepository.delete(franchise);
        HttpStatus status;
        status = HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(franchise, status);
    }
}
