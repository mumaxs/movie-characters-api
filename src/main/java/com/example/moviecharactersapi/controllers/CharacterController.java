package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;


}
