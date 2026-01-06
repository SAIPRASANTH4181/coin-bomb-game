package com.example.coin_bomb_backend.DTO;

public class GameStateDTO {
    private Long gameId;
    private int width;
    private int height;
    private int level;
    private int score;
    private int lives;
    private int totalBombs;
    private String status;
    private String playerName;

    public GameStateDTO(Long gameId, int width, int height, int level, int score, int lives, int totalBombs, String status, String playerName) {
        this.gameId = gameId;
        this.width = width;
        this.height = height;
        this.level = level;
        this.score = score;
        this.lives = lives;
        this.totalBombs = totalBombs;
        this.status = status;
        this.playerName = playerName;
    }

    // Getters and Setters
    public Long getGameId() { return gameId; }
    public void setGameId(Long gameId) { this.gameId = gameId; }
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getLives() { return lives; }
    public void setLives(int lives) { this.lives = lives; }
    public int getTotalBombs() { return totalBombs; }
    public void setTotalBombs(int totalBombs) { this.totalBombs = totalBombs; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
}
