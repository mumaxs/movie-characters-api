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

    /**
     * Adding a character to the database
     * @param character : Character.
     * @return : New character object.
     */
    @PostMapping
    public ResponseEntity<Character> addCharacter(@RequestBody Character character) {
        Character returnCharacter = characterRepository.save(character);
        HttpStatus status = HttpStatus.CREATED;
        return new ResponseEntity<>(returnCharacter, status);
    }

    /**
     * Fetching all the current characters from the database.
     * @return : Returns a List of characters.
     */
    @GetMapping()
    public ResponseEntity<List<Character>> getAllCharacters() {
        List<Character> characters = characterRepository.findAll();
        HttpStatus resp = HttpStatus.OK;
        return new ResponseEntity<>(characters, resp);
    }

    /**
     * Fetch a character from the database with a given id.
     * @param id : Id identifier.
     * @return : Returns the character from given id.
     */
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

    /**
     * Updates a character in the database.
     * @param id : Given id for a specific character.
     * @param character : Character object with the updates.
     * @return : Returns an updated character object.
     */
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

    /**
     * Deleting a character from the database.
     * @param id : Given id for a specific character.
     * @return : Returns HTTP status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Character> deleteCharacterById(@PathVariable int id) {
        HttpStatus status;
        if (characterRepository.existsById(id)) {
            characterRepository.deleteById(id);
            status = HttpStatus.OK;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }
}
