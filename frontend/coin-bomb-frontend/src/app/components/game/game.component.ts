import { Component, OnInit } from '@angular/core';
import { GameService } from 'src/app/services/game.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { GameStateDTO, GridSquare, SquareRevealDTO, GameInitDTO } from 'src/app/models/game-and-square-dtos.interface';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  gameStatus: string = 'IN_PROGRESS';
  isLoading: boolean = false;
  errorMessage: string = '';

  // Game State
  gameId: number = 0;
  gameWidth: number = 0;
  gameHeight: number = 0;
  grid: GridSquare[][] | null = null;

  // Player & Progression
  playerName: string = '';
  isPlayerNameSet: boolean = false;
  level: number = 1;
  score: number = 0;
  lives: number = 3;
  totalBombs: number = 0;

  constructor(
    private gameService: GameService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Check if user is already logged in
    const user = this.authService.getUser();
    if (user) {
      this.playerName = user.username;
      this.isPlayerNameSet = true;
      this.initializeGame();
    }
  }

  startGame(): void {
    if (!this.playerName.trim()) {
      this.errorMessage = 'Please enter your name!';
      return;
    }
    this.isPlayerNameSet = true;
    this.initializeGame();
  }

  initializeGame(): void {
    this.isLoading = true;
    this.errorMessage = '';
    this.gameService.initializeGame(this.playerName).subscribe({
      next: (response: GameInitDTO) => {
        this.gameId = response.id;
        this.gameWidth = response.width;
        this.gameHeight = response.height;
        this.updateGameState(); // Fetch full state
        this.createGrid();
        this.isLoading = false;
      },
      error: (error: any) => {
        console.error('Error initializing game', error);
        this.errorMessage = 'Could not start game. Is the backend running?';
        this.isLoading = false;
      }
    });
  }

  updateGameState(): void {
    this.gameService.getGameState(this.gameId).subscribe({
      next: (state: GameStateDTO) => {
        this.level = state.level;
        this.score = state.score;
        this.lives = state.lives;
        this.totalBombs = state.totalBombs;
        this.gameStatus = state.status;
      }
    });
  }

  createGrid(): void {
    this.grid = Array(this.gameHeight).fill(null).map(() =>
      Array(this.gameWidth).fill(null).map(() => ({ revealed: false, content: '' }))
    );
  }

  onSquareClick(x: number, y: number): void {
    if (this.grid && this.gameId &&
      x >= 0 && x < this.grid.length &&
      y >= 0 && y < this.grid[x].length &&
      !this.grid[x][y].revealed &&
      this.gameStatus === 'IN_PROGRESS') {

      this.gameService.revealSquare(this.gameId, x, y).subscribe({
        next: (response: SquareRevealDTO) => {
          if (this.grid && this.grid[x] && this.grid[x][y]) {
            this.grid[x][y].revealed = true;
            this.grid[x][y].content = response.content;

            // Update full state to sync lives/score
            this.updateGameState();
          }
        },
        error: (error: any) => console.error('Error revealing square', error)
      });
    }
  }

  nextLevel(): void {
    this.isLoading = true;
    this.gameService.nextLevel(this.gameId).subscribe({
      next: (response: GameInitDTO) => {
        this.gameWidth = response.width;
        this.gameHeight = response.height;
        this.createGrid();
        this.updateGameState();
        this.isLoading = false;
      },
      error: (err: any) => {
        console.error(err);
        this.isLoading = false;
      }
    });
  }

  restartGame(): void {
    this.initializeGame();
  }

  logout(): void {
    this.authService.logout().subscribe({
      next: () => {
        this.isPlayerNameSet = false;
        this.playerName = '';
        this.router.navigate(['/signin']);
      },
      error: (err: any) => {
        console.error('Logout error', err);
        // Even if there's an error, still redirect to login page
        this.isPlayerNameSet = false;
        this.playerName = '';
        this.router.navigate(['/signin']);
      }
    });
  }
}
