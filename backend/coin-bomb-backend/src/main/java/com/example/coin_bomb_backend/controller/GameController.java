package com.example.coin_bomb_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @GetMapping("/api/status")
    public String getStatus() {
        return "Game is running!";
    }
}
