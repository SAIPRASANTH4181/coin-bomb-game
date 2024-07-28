import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) { }

  onSignup() {
    this.authService.signup(this.username, this.password)
      .subscribe(response => {
        console.log('Signup successful', response);
      }, error => {
        console.error('Signup failed', error);
      });
  }

  ngOnInit(): void {
  }

}
