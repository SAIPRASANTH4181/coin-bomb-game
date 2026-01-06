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
	@Autowired
    private SquareRepository squareRepository;
    
    @Autowired
    private com.example.coin_bomb_backend.repository.PlayerRepository playerRepository;
    
    @Autowired
    private GameLevelService levelService;

    public Game createGame(String playerName) {
        Player player = playerRepository.findByUsername(playerName)
            .orElseGet(() -> playerRepository.save(new Player(playerName)));

        Game game = new Game();
        game.setPlayer(player);
        game.setCurrentLevel(1);
        game.setLives(3); // Initial lives
        game.setScore(0);
        
        setupLevel(game, 1);
        
        return gameRepository.save(game);
    }

    private void setupLevel(Game game, int level) {
        int size = levelService.getGridSizeForLevel(level);
        int bombs = levelService.getBombCountForLevel(level);
        
        game.setWidth(size);
        game.setHeight(size);
        game.setTotalBombs(bombs);
        game.setSquares(new ArrayList<>());
        
        // Clear existing squares if any (for next level)
        // Note: In a real app, we might want to soft delete or archive old levels
        
        addBombsToGame(game, bombs);
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
	}
	

    public SquareRevealDTO revealSquare(Long gameId, int x, int y) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new RuntimeException("Game not found"));

        if (game.getStatus() != GameStatus.IN_PROGRESS) {
             return buildRevealDTO(game, x, y, false);
        }

        Square square = game.getSquares().stream()
            .filter(s -> s.getX() == x && s.getY() == y)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Square not found"));
        
        if (square.isRevealed()) {
             return buildRevealDTO(game, x, y, true);
        }

        square.setRevealed(true);
        
        if (square.isBomb()) {
            game.setLives(game.getLives() - 1);
            if (game.getLives() <= 0) {
                game.setStatus(GameStatus.LOST);
            }
        } else {
            game.setScore(game.getScore() + 10); // 10 points per coin
            
            // Check win condition (all non-bombs revealed)
            boolean allCoinsRevealed = game.getSquares().stream()
                .filter(s -> !s.isBomb())
                .allMatch(Square::isRevealed);
            
            if (allCoinsRevealed) {
                // Level Complete!
                // For now, we can set status to WON, but frontend should offer "Next Level"
                game.setStatus(GameStatus.WON); 
            }
        }

        squareRepository.save(square);
        gameRepository.save(game);
        
        return buildRevealDTO(game, x, y, true);
    }

    private SquareRevealDTO buildRevealDTO(Game game, int x, int y, boolean isAction) {
        // Find the specific square to return content
        // If we just lost, maybe we want to reveal everything? For now keep simple.
        Square square = game.getSquares().stream()
                .filter(s -> s.getX() == x && s.getY() == y)
                .findFirst()
                .orElse(null);
        
        String content = "";
        boolean revealed = false;
        if (square != null) {
            revealed = square.isRevealed();
            if (revealed) {
                content = square.isBomb() ? "Bomb" : "Coin";
            }
        }
        
        // We need to pass back the updated game state (lives, score, level)
        // The current SquareRevealDTO is too simple. We should probably return a richer object 
        // OR pack it into the existing DTO.
        // Let's pack it into the existing DTO for now to minimize breaking changes, 
        // but ideally we should use a GameStateResponse.
        
        SquareRevealDTO dto = new SquareRevealDTO(x, y, revealed, content, game.getStatus().toString());
        // We will need to extend DTO to carry lives/score info or fetch it separately.
        // For this step, let's just return the basic reveal info. 
        // The frontend will need to poll or we need to update the DTO.
        return dto;
    }

    public Game nextLevel(Long gameId) {
        Game game = gameRepository.findById(gameId)
            .orElseThrow(() -> new RuntimeException("Game not found"));
            
        if (game.getStatus() == GameStatus.WON) {
            game.setCurrentLevel(game.getCurrentLevel() + 1);
            game.setStatus(GameStatus.IN_PROGRESS);
            
            // Clean up old squares
            game.getSquares().clear(); // Orphan removal should handle DB delete
            
            setupLevel(game, game.getCurrentLevel());
            return gameRepository.save(game);
        }
        return game;
    }
	 
	 
	 
}
