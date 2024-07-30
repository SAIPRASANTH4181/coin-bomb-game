package com.example.coin_bomb_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.coin_bomb_backend.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long>{

}
