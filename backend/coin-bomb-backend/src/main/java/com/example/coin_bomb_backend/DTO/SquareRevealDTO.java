package com.example.coin_bomb_backend.DTO;

public class SquareRevealDTO {
	
	private int x;
    private int y;
    private boolean revealed;
    private String content; // "Bomb" or "Coin"

    public SquareRevealDTO() {}

    public SquareRevealDTO(int x, int y, boolean revealed, String content) {
        this.x = x;
        this.y = y;
        this.revealed = revealed;
        this.content = content;
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

    
    

}
