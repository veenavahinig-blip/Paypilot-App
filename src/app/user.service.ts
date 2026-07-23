 
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
 
@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  baseUrl = 'http://localhost:8086/users';
 
  constructor(private http: HttpClient) { }
 
  login(data: any) {
    return this.http.post(this.baseUrl+"/login",
      data,
      { responseType: 'text' }
    );
  }

  registerUser(user:any){
    return this.http.post(this.baseUrl + "/register",user,{ responseType: 'text' });
  }
 
 
  forgotPassword(email: string) {
    return this.http.post(this.baseUrl+"/forgotPassword",
      { email:email },
      { responseType: 'text' }
    );
  }
 
  verifyOtp(email: string, otp: string) {
    return this.http.post(
      `${this.baseUrl}/verify-otp`,
      { email, otp },
      { responseType: 'text' }
    );
  }
 
  resendOtp(email: string) {
    return this.http.post(this.baseUrl+"/forgotPassword",
      { email:email },
      { responseType: 'text' }
    );
  }

  verifyOtpAndResetPassword(email:String ,otp:String,newPassword:String){
          return this.http.post(this.baseUrl+"/verifyOtpAndResetPassword",{ email:email,otp:otp,newPassword:newPassword},{ responseType: 'text' });
 
  }

 
  getDashboard(email:string){


  return this.http.get<any>(

    `${this.baseUrl}/dashboard/${email}`

  );


}
 
 
 


 
}
 