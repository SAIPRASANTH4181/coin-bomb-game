package com.example.coin_bomb_backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.coin_bomb_backend.model.Game;
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
	
}
