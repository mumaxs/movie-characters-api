package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @GetMapping("/allCharacters")
    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        Character returnCharacter = characterRepository.save(character);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnCharacter, status);
    }
}
