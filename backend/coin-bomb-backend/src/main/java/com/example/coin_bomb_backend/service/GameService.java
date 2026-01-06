package com.example.coin_bomb_backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coin_bomb_backend.DTO.SquareRevealDTO;
import com.example.coin_bomb_backend.model.Game;
import com.example.coin_bomb_backend.model.GameStatus;
import com.example.coin_bomb_backend.model.Square;
import com.example.coin_bomb_backend.repository.GameRepository;
import com.example.coin_bomb_backend.repository.SquareRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
    private SquareRepository squareRepository;
	
	public Game createGame(int width, int height) {
        Game game = new Game();
        game.setWidth(width);
        game.setHeight(height);
        game.setSquares(new ArrayList<>());
        return gameRepository.save(game);
    }
	
	public void addBombsToGame(Game game, int numberOfBombs) {
	    int width = game.getWidth();
	    int height = game.getHeight();
	    int totalSquares = width * height;
	    
	    List<Integer> indices = IntStream.range(0, totalSquares).boxed().collect(Collectors.toList());
	    Collections.shuffle(indices);

	    for (int i = 0; i < totalSquares; i++) {
	        int index = indices.get(i);
	        int row = index / width;
	        int col = index % width;
	        
	        Square square = new Square();
	        square.setX(row);
	        square.setY(col);
	        square.setBomb(i < numberOfBombs);
	        square.setGame(game);
	        game.getSquares().add(square);
	    }

	    gameRepository.save(game);
	}
	

    public SquareRevealDTO revealSquare(Long gameId, int x, int y) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new RuntimeException("Game not found"));

        if (game.getStatus() != GameStatus.IN_PROGRESS) {
             // If game is over, just return the requested square's state (or empty if not found)
             // For simplicity, we'll try to find the square to return its current state, 
             // but we won't change anything.
             Square square = game.getSquares().stream()
                .filter(s -> s.getX() == x && s.getY() == y)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Square not found"));
             String content = square.isBomb() ? "Bomb" : "Coin";
             return new SquareRevealDTO(x, y, square.isRevealed(), content, game.getStatus().toString());
        }

        Square square = game.getSquares().stream()
            .filter(s -> s.getX() == x && s.getY() == y)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Square not found"));
        
        if (square.isRevealed()) {
             String content = square.isBomb() ? "Bomb" : "Coin";
             return new SquareRevealDTO(x, y, square.isRevealed(), content, game.getStatus().toString());
        }

        square.setRevealed(true);
        
        if (square.isBomb()) {
            game.setStatus(GameStatus.LOST);
            // Optional: Reveal all bombs? For now, just the one clicked.
        } else {
            // Check win condition
            boolean allCoinsRevealed = game.getSquares().stream()
                .filter(s -> !s.isBomb())
                .allMatch(Square::isRevealed);
            
            if (allCoinsRevealed) {
                game.setStatus(GameStatus.WON);
            }
        }

        squareRepository.save(square);
        gameRepository.save(game);
        
        String content = square.isBomb() ? "Bomb" : "Coin";
        return new SquareRevealDTO(x, y, square.isRevealed(), content, game.getStatus().toString());
    }
	 
	 
	 
}
