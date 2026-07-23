import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
 
@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent implements OnInit {
 
  email: string = '';
 
  constructor(
    private userService: UserService,
    private router: Router
  ) { }
 
  ngOnInit(): void {
 
  }
 
  sendOtp() {
 
 
    this.userService.forgotPassword(this.email)
      .subscribe({
 
        next: (response) => {
 
          alert(response);
 
          this.router.navigate(
            ['/verify-otp'],
            {
              queryParams: {
                email: this.email
              }
            }
          );
 
        },
 
        error: () => {
 
          alert('Failed to send OTP');
 
        }
 
      });
 
  }
 
}
 