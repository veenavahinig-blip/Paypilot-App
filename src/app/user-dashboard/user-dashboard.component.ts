import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';

import { UserService } from '../user.service';
import { NotificationService } from '../notification.service';


@Component({

  selector: 'app-user-dashboard',

  templateUrl: './user-dashboard.component.html',

  styleUrls: ['./user-dashboard.component.css']

})

export class UserDashboardComponent implements OnInit {


  userId: string = '';


  userName: string = '';


  userEmail: string = '';


  totalBills: number = 0;


  upcomingBills: number = 0;


  overdueBills: number = 0;


  paidBills: number = 0;


  recentBills: any[] = [];

  notifications: any[] = [];
 
  unreadNotifications: any[] = [];
 
  showNotificationPopup = false;
 
 


  constructor(

    private router: Router,

    private userService: UserService,

    private notificationService:NotificationService

  ) { }


  ngOnInit(): void {


  const email =

    sessionStorage.getItem('email');


  console.log(

    'Email from Session Storage:',

    email

  );


  if (email) {


    this.loadDashboardData(email);


  } else {


    console.error(

      'Email Not Found In Session Storage'

    );


    this.router.navigate(

      ['/login']

    );


  }


}
 


  loadDashboardData(email: string): void {


    this.userService

      .getDashboard(email)

      .subscribe({


        next: (response: any) => {


          console.log(

            'Dashboard Response',

            response

          );


          this.userId =

            response.userId;
          sessionStorage.setItem('userId',response.userId);
          this.loadNotifications();

          


          this.userName =

            response.userName;


          this.userEmail =

            response.userEmail;


          this.totalBills =

            response.totalBills;


          this.upcomingBills =

            response.upcomingBills;


          this.overdueBills =

            response.overdueBills;


          this.paidBills =

            response.paidBills;


          this.recentBills =

            response.recentBills;



        },


        error: (error) => {


          console.error(

            'Dashboard Load Error',

            error

          );


        }


      });


  }

  

  loadNotifications() {


  console.log('User Id = ', this.userId);


  if (!this.userId) {

    console.log('User Id Missing');

    return;

  }


  this.notificationService

      .getNotifications(this.userId)

      .subscribe({


        next: (res: any[]) => {


          console.log(

            'Notification Response',

            res

          );


          this.notifications = res || [];


          this.unreadNotifications =

              this.notifications.filter(

                n => !n.isRead

              );


          console.log(

            'Unread Notifications',

            this.unreadNotifications

          );


        },


        error: (err) => {


          console.error(

            'Notification Error',

            err

          );


        }


      });


}
 
 
 
  toggleNotifications() {
 
    this.showNotificationPopup = !this.showNotificationPopup;
 
  }
 
 
  markAsRead(id: number) {
 
  this.notificationService.markAsRead(id).subscribe({
 
    next: () => {
 
 
      this.notifications =
 
        this.notifications.filter(
 
          n => n.notificationId !== id
 
        );
 
 
      this.unreadNotifications =
 
        this.unreadNotifications.filter(
 
          n => n.notificationId !== id
 
        );
 
 
    },
 
    error: (err) => {
 
      console.error(err);
 
    }
 
  });
 
}
 
 
 


  openProfile(): void {


    this.router.navigate(

      ['/profile'],

      {

        queryParams: {

          email: this.userEmail

        }

      }

    );


  }


  addNewBill(): void {


    this.router.navigate(

      ['/manage-bills'],

      {

        queryParams: {

          email: this.userEmail

        }

      }

    );


  }


  setReminder(): void {


    this.router.navigate(

      ['/schedule-payments'],

      {

        queryParams: {

          email: this.userEmail

        }

      }

    );


  }


  viewReports(): void {


    this.router.navigate(

      ['/transactions'],

      {

        queryParams: {

          email: this.userEmail

        }

      }

    );


  }


  logout(): void {


    sessionStorage.clear();


    localStorage.clear();


    this.router.navigate(['/login']);


  }


}
 