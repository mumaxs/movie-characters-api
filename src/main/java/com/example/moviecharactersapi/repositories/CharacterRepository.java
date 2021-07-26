package com.example.moviecharactersapi.repositories;

import com.example.moviecharactersapi.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository <Character, Integer> {
}
