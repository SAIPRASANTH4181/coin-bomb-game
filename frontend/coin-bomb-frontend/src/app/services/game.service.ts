import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GameInitDTO , SquareDTO, SquareRevealDTO} from '../models/game-and-square-dtos.interface';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private baseUrl = environment.baseUrl; // Use environment baseUrl variable

  constructor(private http: HttpClient) { }
  
  initializeGame(): Observable<GameInitDTO> {
    return this.http.post<GameInitDTO>(`${this.baseUrl}/api/initialize`, {});
  }

  getSquaresByGameId(gameId: number): Observable<SquareDTO[]> {
    return this.http.get<SquareDTO[]>(`${this.baseUrl}/api/game/${gameId}/squares`);
}
  

revealSquare(gameId: number, x: number, y: number): Observable<SquareRevealDTO> {
  const request = { x, y };
  return this.http.post<SquareRevealDTO>(`${this.baseUrl}/api/game/${gameId}/reveal`, request);
}

}
