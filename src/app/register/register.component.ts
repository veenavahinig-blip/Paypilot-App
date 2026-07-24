import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { RecaptchaService } from '../recaptcha.service';
import { environment } from '../../environments/environment';
 
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
   
  registerForm: FormGroup;
  environment = environment;
  registerRecaptchaWidgetId: any;
 
  constructor(private fb: FormBuilder,private router:Router,private service:UserService, private recaptchaService: RecaptchaService) {
  
    this.registerForm = this.fb.group({
 
      userName: ['', [
        Validators.required,
        Validators.pattern('^[a-zA-Z ]+$')
      ]],
 
      userEmail: ['', [
        Validators.required,
        Validators.email
      ]],
 
      gender: ['', Validators.required],
 
      panDetails: ['', [
        Validators.required,
        Validators.pattern('^[A-Z]{5}[0-9]{4}[A-Z]{1}$')
      ]],
 
      bankAccountNumber: ['', [
        Validators.required,
        Validators.pattern('^[0-9]{9,18}$')
      ]],
 
      ifscCode: ['', [
        Validators.required,
        Validators.pattern('^[A-Z]{4}0[A-Z0-9]{6}$')
      ]],
      bankingPartner: ['', Validators.required],
      password: ['', [
        Validators.required,
        Validators.pattern(
          '^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$'
        )
      ]],
      confirmPassword: ['', Validators.required],
      otp: ['']
    });
 
  }
 
  ngOnInit() {
    this.recaptchaService.loadScript();
  }
 
  get f(){
    return this.registerForm.controls;
  }
 
  ngAfterViewInit() {
    this.recaptchaService.render('register-recaptcha', environment.recaptchaSiteKey)
      .then((widgetId: any) => {
        this.registerRecaptchaWidgetId = widgetId;
        console.log('reCAPTCHA widget rendered, id:', widgetId);
      })
      .catch(err => {
        console.error('Failed to render reCAPTCHA widget:', err);
      });
  }
 
  private getRecaptchaToken(): Promise<string> {
    return this.recaptchaService.getToken(this.registerRecaptchaWidgetId);
  }
 
  sendOtp() {
    if (this.registerForm.valid) {
      if (this.f.password.value !== this.f.confirmPassword.value) {
        alert("Passwords do not match");
        return;
      }
 
      this.getRecaptchaToken().then((captchaToken: string) => {
        this.service.registerUser({
          ...this.registerForm.value,
          captchaToken
        }).subscribe({
          next: (res) => {
            console.log(res);
            if (res === "OTP SENT TO MAIL") {
              alert("OTP Sent Successfully");
            } else {
              alert(res);
            }
          },
          error: (err) => {
            console.log(err);
            alert("Error Occurred");
          }
        });
      }).catch((err) => {
        alert(err || 'reCAPTCHA verification failed');
      });
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
 
  register() {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }
 
    this.getRecaptchaToken().then((captchaToken: string) => {
      const data = {
        ...this.registerForm.value,
        captchaToken
      };
      console.log(data);
      alert("Registration Successful");
      this.router.navigate(['/login'])
    }).catch((err) => {
      alert(err || 'reCAPTCHA verification failed');
    });
  }
 
  ngOnDestroy() {
    this.recaptchaService.reset(this.registerRecaptchaWidgetId);
  }
}
