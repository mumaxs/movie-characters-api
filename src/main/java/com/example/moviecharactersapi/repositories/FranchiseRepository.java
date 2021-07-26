package com.example.moviecharactersapi.repositories;

import com.example.moviecharactersapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository <Franchise, Integer> {
}
