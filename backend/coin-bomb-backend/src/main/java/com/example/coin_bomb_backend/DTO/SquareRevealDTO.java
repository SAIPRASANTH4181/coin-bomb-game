package com.example.coin_bomb_backend.DTO;

public class SquareRevealDTO {
	
	private int x;
    private int y;
    private boolean revealed;
    private String content; // "Bomb" or "Coin"
    private String gameStatus; // "IN_PROGRESS", "WON", "LOST"

    public SquareRevealDTO() {}

    public SquareRevealDTO(int x, int y, boolean revealed, String content, String gameStatus) {
        this.x = x;
        this.y = y;
        this.revealed = revealed;
        this.content = content;
        this.gameStatus = gameStatus;
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

	public boolean isRevealed() {
		return revealed;
	}

	public void setRevealed(boolean revealed) {
		this.revealed = revealed;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    
    

}
