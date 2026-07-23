import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user.service';
import { RecaptchaService } from '../recaptcha.service';
import { environment } from '../../environments/environment';
 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
   
  loginForm!: FormGroup;
 
  showPassword: boolean = false;
  environment = environment;
  loginRecaptchaWidgetId: any;
 
  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router,
    private recaptchaService: RecaptchaService
  ) {
    this.loginForm = this.fb.group({
      email: ['',[Validators.required,Validators.pattern('^[a-zA-Z0-9@.]+$')]],
      password: ['',[Validators.required,Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$')]],
    });
  }
 
  ngOnInit() {
    this.recaptchaService.loadScript();
  }
 
  ngAfterViewInit() {
    this.recaptchaService.render('login-recaptcha', environment.recaptchaSiteKey)
      .then((widgetId: any) => {
        this.loginRecaptchaWidgetId = widgetId;
        console.log('reCAPTCHA widget rendered, id:', widgetId);
      })
      .catch(err => {
        console.error('Failed to render reCAPTCHA widget:', err);
      });
  }
 
  private getRecaptchaToken(): Promise<string> {
    return this.recaptchaService.getToken(this.loginRecaptchaWidgetId);
  }
 
  login() {
    if (this.loginForm.invalid) {
      this.loginForm.markAllAsTouched();
      return;
    }
 
    this.getRecaptchaToken().then((captchaToken: string) => {
      const emailValue = this.loginForm.value.email;
      const data = {
        email: emailValue,
        userEmail: emailValue,
        password: this.loginForm.value.password,
        captchaToken
      };
      console.log('Login request payload:', data);
 
      this.userService.login(data).subscribe({
        next: (response: any) => {
          alert(response);
          if (response === "Login Successfully") {
            const emailValue=this.loginForm.value.email
            sessionStorage.setItem('email',emailValue);

            this.router.navigate(['/user-dashboard']);
 
          }
        },
        error: (err) => {
          const message = err && err.error ? err.error : (err && err.message ? err.message : 'Login Failed');
          alert('Login Failed: ' + message);
          console.error('Login error:', err);
        }
      });
    }).catch((err) => {
      alert(err || 'reCAPTCHA verification failed');
    });
  }
 
  togglePassword() {
    this.showPassword = !this.showPassword;
  }
 
  ngOnDestroy() {
    this.recaptchaService.reset(this.loginRecaptchaWidgetId);
  }
}
