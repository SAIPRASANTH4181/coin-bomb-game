package com.example.coin_bomb_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coin_bomb_backend.model.Game;
import com.example.coin_bomb_backend.service.GameService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
	
	@Autowired
    private GameService gameService;
	
	@GetMapping("/initialize")
    public String initializeGame() {
        Game game = gameService.createGame(7, 7); // Create a game with 7x7 grid
        gameService.addBombsToGame(game, 5); // Add 5 bombs to the game
        return "Game initialized with 7x7 grid and 5 bombs.";
    }

    @GetMapping("/status")
    public String getStatus() {
        return "Game is running!";
    }
}
