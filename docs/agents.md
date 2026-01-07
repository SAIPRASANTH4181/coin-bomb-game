# Project Context & Game Mechanics

## Game Overview
"Coin Bomb" is a level-based strategy game where players reveal squares on a grid to find gold while avoiding hidden mines.

## Core Mechanics
### 1. Levels & Progression
- **Starting State:** User starts with a set number of lives.
- **Level Up:** Successfully clearing a level advances the player.
- **Difficulty Scaling:**
    - **Mines:** Number of mines increases with levels.
    - **Gold:** Number of gold slots decreases (or density changes) making prediction harder.
    - **Grid Size:** The grid dimensions ($N \times N$) increase every couple of levels (e.g., Level 1-2: 4x4, Level 3-4: 5x5).

### 2. Gameplay Loop
- Player selects a square on the grid.
- **Result:**
    - **Gold:** Score increases.
    - **Mine:** Life lost.
- **Win Condition:** Reveal all gold squares (or reach a specific threshold) to advance.
- **Lose Condition:** Run out of lives.

### 3. Ranking System
- Players are ranked based on:
    1. **Highest Level Reached** (Primary)
    2. **Time Taken** (Secondary - faster is better)

## Architecture
### Backend (Spring Boot)
- **Game Engine:** Logic for grid generation, mine placement, and move validation.
- **State Management:** Tracks current level, lives, grid state, and score.
- **API:** REST endpoints for:
    - `POST /game/start`: Initialize a new game.
    - `POST /game/move`: Process a player's move.
    - `GET /game/status`: Get current game state.
    - `GET /leaderboard`: Fetch rankings.

### Frontend (Angular)
- **Game Board:** Dynamic grid component rendering squares based on current level size.
- **Status Panel:** Displays Lives, Level, Score.
- **Leaderboard:** Table showing top players.

## Current State
- **Backend:** Basic structure exists.
- **Frontend:** Basic structure exists.
- **Next Steps:** Implement dynamic grid scaling and ranking logic.
