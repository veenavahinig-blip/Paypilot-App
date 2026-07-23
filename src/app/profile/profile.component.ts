import { Component, OnInit } from '@angular/core';

import { ProfileService } from '../profile.service';

@Component({

  selector: 'app-profile',

  templateUrl: './profile.component.html',

  styleUrls: ['./profile.component.css']

})

export class ProfileComponent implements OnInit {


  user: any = {


    userId: '',


    userName: '',


    userEmail: '',


    password: '',


    panDetails: '',


    bankAccountNumber: '',


    ifscCode: '',


    bankingPartner: '',


    role: '',


    otp: '',


    verified: false,


    gender: '',


    balance: 0


  };


  maskedAccountNumber: string = '';


  constructor(

    private profileService: ProfileService

  ) { }


  ngOnInit() {


    var email = sessionStorage.getItem('email');


    if (email != null) {


      this.loadProfile(email);


    }


  }

  loadProfile(email: string) {


  console.log("Calling Profile API For :", email);


  this.profileService.getProfile(email)

    .subscribe(


      (response: any) => {


        console.log("Profile Data :", response);


        this.user = response;


        this.maskAccountNumber();


      },


      (error: any) => {


        console.log("Profile Error :", error);


        alert("Unable To Load Profile");


      }


    );

}
 


  maskAccountNumber() {


    if (this.user.bankAccountNumber) {


      this.maskedAccountNumber =

        'XXXXXXXX' +

        this.user.bankAccountNumber.substring(

          this.user.bankAccountNumber.length - 4

        );


    }


  }


  updateProfile() {


    var updateRequest = {


      userName: this.user.userName,


      gender: this.user.gender


    };


    this.profileService

      .updateProfile(

        this.user.userEmail,

        updateRequest

      )

      .subscribe(


        (response: any) => {


          alert(

            'Profile Updated Successfully'

          );


        },


        (error: any) => {


          console.log(error);


          alert(

            'Profile Update Failed'

          );


        }


      );


  }


}
 