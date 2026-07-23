import { Component, OnInit } from '@angular/core';

import { PaymentService } from '../payment.service';


@Component({

  selector: 'app-track-payments',

  templateUrl: './track-payments.component.html',

  styleUrls: ['./track-payments.component.css']

})

export class TrackPaymentsComponent implements OnInit {

  selectedPayment: any = null;
  showPaymentModal = false;


  userId: string = '';


  transactions: any[] = [];


  filteredTransactions: any[] = [];


  paymentIdFilter = '';


  billIdFilter = '';


  statusFilter = '';


  fromDate = '';


  toDate = '';


  constructor(

    private paymentService: PaymentService

  ) {}


  ngOnInit(): void {


    const storedUserId =

      sessionStorage.getItem('userId');


    if (storedUserId) {


      this.userId = storedUserId;


      this.loadTransactions();


    }


  }


  loadTransactions(): void {


    this.paymentService

      .getPaymentHistory(this.userId)

      .subscribe({


        next: (response: any[]) => {


          this.transactions =

            response.sort(

              (a: any, b: any) =>

                new Date(b.paymentDate).getTime()

                -

                new Date(a.paymentDate).getTime()

            );


          this.filteredTransactions =

            this.transactions.slice(0, 10);


        },


        error: (error) => {


          console.error(error);


        }


      });


  }


  applyFilters(): void {


    this.filteredTransactions =

      this.transactions.filter(tx => {


        const matchPaymentId =

  !this.paymentIdFilter ||

  tx.paymentId === Number(this.paymentIdFilter);


const matchBillId =

  !this.billIdFilter ||

  tx.billId === Number(this.billIdFilter);
 


        const matchStatus =

          !this.statusFilter ||


          tx.paymentStatus ===

          this.statusFilter;


        let txDate = '';


        if (tx.paymentDate) {


          txDate =

            tx.paymentDate.substring(

              0,

              10

            );


        }


        const matchFromDate =

          !this.fromDate ||

          txDate >= this.fromDate;


        const matchToDate =

          !this.toDate ||

          txDate <= this.toDate;


        return (


          matchPaymentId &&

          matchBillId &&

          matchStatus &&

          matchFromDate &&

          matchToDate


        );


      });


  }


  clearFilters(): void {


    this.paymentIdFilter = '';


    this.billIdFilter = '';


    this.statusFilter = '';


    this.fromDate = '';


    this.toDate = '';


    this.filteredTransactions =

      this.transactions.slice(0, 10);


  }
  openPaymentDetails(payment: any): void {


  this.selectedPayment = payment;


  this.showPaymentModal = true;


}
 
closePaymentDetails(): void {


  this.showPaymentModal = false;


  this.selectedPayment = null;


}
 


}
 