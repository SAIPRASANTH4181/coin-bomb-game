package com.example.coin_bomb_backend.DTO;

public class SquareRevealRequestDTO {

	private int x;
    private int y;

    public SquareRevealRequestDTO(){}

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

	public SquareRevealRequestDTO(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
}
