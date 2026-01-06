package com.example.coin_bomb_backend.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
public class Game {

	@jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private int width;
	
	private int height;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Square> squares;

    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    private GameStatus status = GameStatus.IN_PROGRESS;

    @jakarta.persistence.ManyToOne
    @jakarta.persistence.JoinColumn(name = "player_id")
    private Player player;

    private int currentLevel = 1;
    private int score = 0;
    private int lives = 3;
    private int totalBombs;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getTotalBombs() {
        return totalBombs;
    }

    public void setTotalBombs(int totalBombs) {
        this.totalBombs = totalBombs;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

	
	
	public int getWidth() {
		return width;
	}

	public List<Square> getSquares() {
		return squares;
	}

	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Game() {
		
	}
	public Game(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Game [Id=" + Id + ", width=" + width + ", height=" + height + "]";
	}

	public Long getId() {
		return Id;
	}

	
	
	
}
