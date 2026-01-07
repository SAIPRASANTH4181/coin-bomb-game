import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  username: string = '';
  password: string = '';
  isLoginFailed: boolean = false;
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (data: any) => {
        this.authService.setToken(data.token);
        this.authService.setUser({
          id: data.id,
          username: data.username
        });
        this.isLoginFailed = false;
        // Redirect to game page
        this.router.navigate(['/game']);
      },
      error: (err: any) => {
        this.errorMessage = err.error?.message || 'Login failed';
        this.isLoginFailed = true;
      }
    });
  }
}
