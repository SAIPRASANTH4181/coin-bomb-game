import { Component, OnInit } from '@angular/core';
import { SquareRevealDTO, GridSquare } from 'src/app/models/game-and-square-dtos.interface';
import { GameService } from 'src/app/services/game.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  message: string = '';
  level: number = 1; // Default level
  gameData: any; // To store game data 
  gameId: number = 0; // Initialize with a default value
  gameWidth:number=0;
  gameHeight:number=0;
  grid: GridSquare[][] | null = null;
  gameStatus: string = 'IN_PROGRESS';

  constructor(private gameService: GameService) { }

  ngOnInit(): void {
    this.initializeGame();
  }
  
  initializeGame(): void {
    this.gameService.initializeGame().subscribe({
      next: (response) => {
        this.gameId = response.id; // Set the gameId from the response
        this.gameWidth=response.width; 
        this.gameHeight=response.height; 
        this.gameStatus = 'IN_PROGRESS';
        this.createGrid();
      },
      error: (error) => console.error('Error initializing game', error)
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
          this.gameStatus = response.gameStatus;
        }
      },
      error: (error) => console.error('Error revealing square', error)
    });
  }
}

restartGame(): void {
    this.initializeGame();
}

}
