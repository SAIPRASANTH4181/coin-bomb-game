package com.example.coin_bomb_backend.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coin_bomb_backend.DTO.GameInitDTO;
import com.example.coin_bomb_backend.DTO.SquareRevealDTO;
import com.example.coin_bomb_backend.DTO.SquareRevealRequestDTO;
import com.example.coin_bomb_backend.model.Game;
import com.example.coin_bomb_backend.service.GameService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/") // Allow requests from this origin
public class GameController {
	
	@Autowired
    private GameService gameService;
	
	@PostMapping("/initialize")
	public ResponseEntity<GameInitDTO> initializeGame() {
	    Game game = gameService.createGame(7, 7); // Create a game with 7x7 grid
	    gameService.addBombsToGame(game, 5); // Add 5 bombs to the game
	    GameInitDTO initDTO = new GameInitDTO(game.getId(), game.getWidth(), game.getHeight());
	    return ResponseEntity.ok(initDTO);
	}
	


	@PostMapping("/game/{gameId}/reveal")
	public ResponseEntity<SquareRevealDTO> revealSquare(
	        @PathVariable Long gameId,
	        @RequestBody SquareRevealRequestDTO request) {
	    SquareRevealDTO revealDTO = gameService.revealSquare(gameId, request.getX(), request.getY());
	    return ResponseEntity.ok(revealDTO);
	}
    
}
