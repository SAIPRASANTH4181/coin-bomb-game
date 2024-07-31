package com.example.coin_bomb_backend.DTO;

public class GameInitDTO {
	
	private Long id;
    private int width;
    private int height;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getWidth() {
		return width;
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
	public GameInitDTO() {
		
	}
	public GameInitDTO(Long id, int width, int height) {
		super();
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	
    
}
