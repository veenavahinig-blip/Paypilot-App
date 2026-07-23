import { BrowserModule } from '@angular/platform-browser';

import { NgModule } from '@angular/core';
 
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';

import { LoginComponent } from './login/login.component';

import { RegisterComponent } from './register/register.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ForgotComponent } from './forgot/forgot.component';
import { VerifyOtpComponent } from './verify-otp/verify-otp.component';
import { RouterModule } from '@angular/router';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { SchedulePaymentsComponent } from './schedule-payments/schedule-payments.component';
import { TrackPaymentsComponent } from './track-payments/track-payments.component';
import { ManageBillsComponent } from './manage-bills/manage-bills.component';
import { CommonModule } from '@angular/common';
 
@NgModule({
 
  declarations: [
 
    AppComponent,
 
    LoginComponent,
 
    RegisterComponent,
 
    ForgotComponent,
 
    VerifyOtpComponent,
 
    UserDashboardComponent,
 
    ProfileComponent,
 
    SchedulePaymentsComponent,
 
    TrackPaymentsComponent,
 
    ManageBillsComponent
 
  ],
 
  imports: [
 
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule
 
  ],
 
  providers: [],
 
  bootstrap: [AppComponent]
 
})
 
export class AppModule { }

 