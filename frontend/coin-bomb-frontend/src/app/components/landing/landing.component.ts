import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css']
})
export class LandingComponent {
  playerName: string = '';

  constructor(private router: Router) { }

  playAnonymously(): void {
    if (this.playerName.trim()) {
      // Navigate to game page with the player name as a query parameter
      this.router.navigate(['/game'], { queryParams: { name: this.playerName } });
    }
  }
}