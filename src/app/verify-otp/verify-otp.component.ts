import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
 
@Component({
  selector: 'app-verify-otp',
  templateUrl: './verify-otp.component.html',
  styleUrls: ['./verify-otp.component.css']
})
export class VerifyOtpComponent {
 
  verifyForm!: FormGroup;
 
  constructor(
    private fb: FormBuilder,
    private service: UserService,
    private router: Router,
    private route:ActivatedRoute
  ) {}
  ngOnInit() {
      const email = this.route.snapshot.queryParamMap.get('email');
    this.verifyForm = this.fb.group({
 
      email: [email],
 
      otp: ['', Validators.required],
 
      newPassword: ['', [
        Validators.required,
        Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$')
      ]],
 
      confirmPassword: ['', Validators.required]
 
    });
 
  }
 
  resetPassword() {
 
    if (this.verifyForm.invalid) {
      return;
    }
 
    const newPassword =
      this.verifyForm.value.newPassword;
 
    const confirmPassword =
      this.verifyForm.value.confirmPassword;
 
    if (newPassword !== confirmPassword) {
      alert("Passwords do not match");
      return;
    }
 
    this.service.verifyOtpAndResetPassword(
      this.verifyForm.value.email,this.verifyForm.value.otp,this.verifyForm.value.newPassword
    ).subscribe({
 
      next: (res) => {
        alert(res);
        if(res==="Password Reset Successfully"){
          this.router.navigate(['/login']);
        }
      },
 
      error: (err) => {
        alert(err.error);
      }
 
    });
 
  }
 
  resendOtp() {
 
    this.service.resendOtp(
      this.verifyForm.value.email
    ).subscribe({
 
      next: (res) => {
        alert(res);
      }
 
    });
 
  }
 
}
 
 