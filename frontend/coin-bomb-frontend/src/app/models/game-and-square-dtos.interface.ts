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
  }


  export interface SquareDTO {
    id: number;
    x: number;
    y: number;
    isRevealed: boolean;
    content: string | null; // null if not revealed, otherwise 'B', 'C', etc.
  }