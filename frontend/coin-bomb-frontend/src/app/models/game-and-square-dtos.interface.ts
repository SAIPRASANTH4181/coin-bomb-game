export interface GameInitDTO {
  id: number;
  width: number;
  height: number;
}

export interface GridSquare {
  revealed: boolean;
  content: string;
}

export interface SquareRevealDTO {
  x: number;
  y: number;
  revealed: boolean;
  content: string; // "Bomb" or "Coin"
  gameStatus: string;
}

export interface SquareDTO {
  id: number;
  x: number;
  y: number;
  isRevealed: boolean;
  content: string | null; // null if not revealed, otherwise 'B', 'C', etc.
}

export interface GameStateDTO {
  gameId: number;
  width: number;
  height: number;
  level: number;
  score: number;
  lives: number;
  totalBombs: number;
  status: string;
  playerName: string;
}