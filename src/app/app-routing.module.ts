import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
 
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ForgotComponent } from './forgot/forgot.component';
import { VerifyOtpComponent } from './verify-otp/verify-otp.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { SchedulePaymentsComponent } from './schedule-payments/schedule-payments.component';
import { TrackPaymentsComponent } from './track-payments/track-payments.component';
import { ManageBillsComponent } from './manage-bills/manage-bills.component';
 
const routes: Routes = [
 
  {path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
 
  {
    path: 'login',
    component: LoginComponent
  },
 
  {
    path: 'register',
    component: RegisterComponent
  },
 
  {
    path: 'forgot',
    component: ForgotComponent
  },
 
  {
    path: 'verify-otp',
    component: VerifyOtpComponent
  },
  {path:'user-dashboard',component:UserDashboardComponent},
  { path: 'profile', component: ProfileComponent },
  { path: 'schedule-payments', component:SchedulePaymentsComponent },
  { path: 'track-payments', component: TrackPaymentsComponent},
  { path: 'manage-bills', component: ManageBillsComponent},
 
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
 