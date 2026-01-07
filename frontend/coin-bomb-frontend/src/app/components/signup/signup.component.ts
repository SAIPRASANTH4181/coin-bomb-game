import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { debounceTime, distinctUntilChanged, Subject } from 'rxjs';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  username: string = '';
  password: string = '';
  isSignUpFailed: boolean = false;
  errorMessage: string = '';
  usernameAvailable: boolean | null = null;
  private usernameSubject = new Subject<string>();

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    // Set up debounced username availability checking
    this.usernameSubject.pipe(
      debounceTime(500),
      distinctUntilChanged()
    ).subscribe((username: string) => {
      if (username.length >= 3) {
        this.authService.checkUsernameAvailability(username).subscribe({
          next: (response: any) => {
            this.usernameAvailable = response.available;
          },
          error: (err: any) => {
            console.error('Error checking username availability', err);
            this.usernameAvailable = null;
          }
        });
      } else {
        this.usernameAvailable = null;
      }
    });
  }

  checkUsernameAvailability(): void {
    this.usernameSubject.next(this.username);
  }

  onSubmit(): void {
    this.authService.register(this.username, this.password).subscribe({
      next: (data: any) => {
        console.log('Registration successful', data);
        // After successful registration, redirect to login page
        this.router.navigate(['/signin']);
      },
      error: (err: any) => {
        this.errorMessage = err.error?.message || 'Registration failed';
        this.isSignUpFailed = true;
      }
    });
  }
}
