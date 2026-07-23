import { Component, OnInit } from '@angular/core';

import { BillService } from '../bill.service';

@Component({

  selector: 'app-schedule-payments',

  templateUrl: './schedule-payments.component.html',

  styleUrls: ['./schedule-payments.component.css']

})

export class SchedulePaymentsComponent

       implements OnInit {


  bills: any[] = [];


  userId = '';

  totalBills = 0;


autoPayEnabledCount = 0;


pendingBillsCount = 0;
 


  constructor(

    private billService: BillService

  ) {}


  ngOnInit(): void {


    this.userId =

      sessionStorage.getItem('userId') || '';


    this.loadBills();

  }


  loadBills(): void {


  this.billService

      .getPendingBills(this.userId)

      .subscribe(data => {


        this.bills = data;


        this.totalBills = this.bills.length;


        this.autoPayEnabledCount =

          this.bills.filter(

            bill => bill.shedulePayment

          ).length;


        this.pendingBillsCount =

          this.bills.filter(

            bill =>

              bill.billStatus === 'PENDING'

          ).length;


      });

}
 

  enableAutoPay(billId: number): void {

  this.billService.enableAutoPay(billId)

    .subscribe({

      next: () => {

        this.loadBills();

      },

      error: (error) => {

        console.error(error);

      }

    });

}


disableAutoPay(billId: number): void {

  this.billService.disableAutoPay(billId)

    .subscribe({

      next: () => {

        this.loadBills();

      },

      error: (error) => {

        console.error(error);

      }

    });

}
 


  
}
 