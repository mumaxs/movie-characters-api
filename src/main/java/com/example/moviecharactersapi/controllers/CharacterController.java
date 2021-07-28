package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/characters")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        Character returnCharacter = characterRepository.save(character);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnCharacter, status);
    }

    @GetMapping()
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        HttpStatus resp = HttpStatus.OK;
        return new ResponseEntity<>(characters, resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable int id){
        Character returnCharacter = new Character();
        HttpStatus status;

        if (characterRepository.existsById(id)) {
            status = HttpStatus.OK;
            returnCharacter = characterRepository.findById(id).get();
        } else {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(returnCharacter, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable int id, @RequestBody Character character) {
        Character returnCharacter = new Character();
        HttpStatus status;

        if (characterRepository.existsById(id)){
            returnCharacter = characterRepository.save(character);
            status = HttpStatus.NO_CONTENT;
        }else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(returnCharacter, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Character> deleteCharacterById(@PathVariable int id) {
        Character character = characterRepository.getById(id);
        HttpStatus status;

        if (characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
            status = HttpStatus.NO_CONTENT;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }
}
