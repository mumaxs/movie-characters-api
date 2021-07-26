package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FranchiseController {

    @Autowired
    private FranchiseRepository franchiseRepository;

    @GetMapping("/")
    public List<Franchise> getAllFranchises() {
        return franchiseRepository.findAll();
    }
}
