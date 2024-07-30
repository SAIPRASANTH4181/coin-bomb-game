package com.example.coin_bomb_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Square {

	@jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private int x;
    private int y;
    private boolean isBomb;
    private boolean isRevealed;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;
    
    
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isBomb() {
		return isBomb;
	}
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	public boolean isRevealed() {
		return isRevealed;
	}
	public void setRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}
	
	public Square()
	{
		
	}
	
	public Square(int x, int y, boolean isBomb, boolean isRevealed) {
		super();
		this.x = x;
		this.y = y;
		this.isBomb = isBomb;
		this.isRevealed = isRevealed;
	}

	
	
	
}
