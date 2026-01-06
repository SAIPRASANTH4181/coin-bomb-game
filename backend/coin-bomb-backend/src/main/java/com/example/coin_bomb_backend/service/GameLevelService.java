package com.example.coin_bomb_backend.service;

import org.springframework.stereotype.Component;

@Component
public class GameLevelService {

    public int getGridSizeForLevel(int level) {
        // Level 1: 5x5
        // Level 2: 6x6
        // ...
        // Cap at 10x10 or similar?
        return Math.min(5 + (level - 1), 10);
    }

    public int getBombCountForLevel(int level) {
        // Level 1: 3 bombs
        // Level 2: 5 bombs
        // Formula: 3 + (level * 2) maybe?
        return 3 + ((level - 1) * 2);
    }
}
