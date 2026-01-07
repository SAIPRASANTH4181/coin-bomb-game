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
import com.example.coin_bomb_backend.DTO.GameStateDTO;
import com.example.coin_bomb_backend.DTO.SquareRevealDTO;
import com.example.coin_bomb_backend.DTO.SquareRevealRequestDTO;
import com.example.coin_bomb_backend.model.Game;
import com.example.coin_bomb_backend.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost", "https://gridluck.netlify.app"}) // Allow requests from dev, docker, and Netlify origins
public class GameController {
	
	@Autowired
    private GameService gameService;
	
	@PostMapping("/initialize")
	public ResponseEntity<GameInitDTO> initializeGame(@RequestBody String playerName) {
	    Game game = gameService.createGame(playerName);
	    GameInitDTO initDTO = new GameInitDTO(game.getId(), game.getWidth(), game.getHeight());
	    return ResponseEntity.ok(initDTO);
	}

    @GetMapping("/game/{gameId}/state")
    public ResponseEntity<GameStateDTO> getGameState(@PathVariable Long gameId) {
        Game game = gameService.getGame(gameId);
        GameStateDTO gameStateDTO = new GameStateDTO(
            game.getId(),
            game.getWidth(),
            game.getHeight(),
            game.getCurrentLevel(),
            game.getScore(),
            game.getLives(),
            game.getTotalBombs(),
            game.getStatus().toString(),
            game.getPlayer().getUsername()
        );
        return ResponseEntity.ok(gameStateDTO);
    }

    @PostMapping("/game/{gameId}/next-level")
    public ResponseEntity<GameInitDTO> nextLevel(@PathVariable Long gameId) {
        Game game = gameService.nextLevel(gameId);
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
