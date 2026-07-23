import { Component, OnInit } from '@angular/core';

import { BillService } from '../bill.service';


@Component({

  selector: 'app-manage-bills',

  templateUrl: './manage-bills.component.html',

  styleUrls: ['./manage-bills.component.css']

})

export class ManageBillsComponent implements OnInit {


  bills: any[] = [];


  selectedAction: string = '';


  showModal: boolean = false;


  userId: string = '';


  newBill = {
    userId: '',
    billName: '',
    billCategory: '',
    billAmount: '',
    billDueDate: '',
    reminderEnabled: 'YES',
    reminderDate: '',
    billStatus: 'PENDING',
    snoozeDate: ''


  };

  todayDate: string = '';


  constructor(

    private billService: BillService

  ) { }


  ngOnInit(): void {


    const storedUserId =

      sessionStorage.getItem('userId');

      this.todayDate = new Date()

                    .toISOString()

                    .split('T')[0];
 


    if (storedUserId) {


      this.userId = storedUserId;


      this.newBill.userId =

        storedUserId;


      console.log(

        'Logged In User ID : ',

        this.userId

      );


      this.loadBills();


    } else {


      console.error(

        'User ID Not Found In Session Storage'

      );


    }


  }


  loadBills(): void {


  this.billService

      .getUserBills(this.userId)

      .subscribe({


        next: (data: any) => {


          this.bills = data.sort(

            (a: any, b: any) => {


              return new Date(a.billDueDate).getTime()

                     -

                     new Date(b.billDueDate).getTime();


            }

          );


        },


        error: (error: any) => {


          console.error(error);


        }


      });


}
 


  openPopup(action: string): void {


    this.selectedAction = action;


    this.showModal = true;


  }


  closePopup(): void {


    this.showModal = false;


  }


  addBill(): void {


  this.newBill.userId = this.userId;


  console.log('Bill Payload : ', this.newBill);


  if (

    !this.newBill.billName ||

    !this.newBill.billCategory ||

    !this.newBill.billAmount ||

    !this.newBill.billDueDate

  ) {


    alert('Please Fill All Required Fields');


    return;

  }


  this.billService

    .addBill(this.newBill)

    .subscribe({


      next: (response: any) => {


        console.log('API Success : ', response);


        alert('Bill Added Successfully');


        this.loadBills();


        this.closePopup();


        this.resetForm();


      },


      error: (error: any) => {


        console.error('API Error : ', error);


        alert('Failed To Add Bill');


      }


    });


}
 


  setReminder(billId: number): void {


    this.billService

      .setReminder(billId)

      .subscribe({


        next: () => {


          alert(

            'Reminder Set Successfully'

          );


          this.loadBills();


        },


        error: (error: any) => {


          console.error(

            error

          );


        }


      });


  }


  snoozeBill(billId: number): void {


    this.billService

      .snoozeBill(billId)

      .subscribe({


        next: () => {


          alert(

            'Bill Snoozed Successfully'

          );


          this.loadBills();


        },


        error: (error: any) => {


          console.error(

            error

          );


        }


      });


  }


  payBill(bill: any): void {


  if (bill.billStatus === 'SNOOZED') {


    alert(

      'Unsnooze the bill before payment'

    );


    return;

  }


  this.billService

      .payBill(

        this.userId,

        bill.billId

      )

      .subscribe({


        next: (response: any) => {


          alert(response);


          this.loadBills();


        },


        error: (error: any) => {


          console.error(error);


          alert('Payment Failed');


        }


      });


}

  unSnoozeBill(billId: number): void {


  this.billService

      .unSnoozeBill(billId)

      .subscribe({


        next: (response: any) => {


          alert(response);


          this.loadBills();


        },


        error: (error: any) => {


          console.error(error);


        }


      });


}
 
 
 


  resetForm(): void {


    this.newBill = {


      userId: this.userId,


      billName: '',


      billCategory: '',


      billAmount: '',


      billDueDate: '',


      reminderEnabled: 'YES',


      reminderDate: '',


      billStatus: 'PENDING',


      snoozeDate: ''


    };


  }

  pendingBills(): any[] {


  return this.bills.filter(


    (bill: any) =>


      bill.billStatus &&


      (


        bill.billStatus.toUpperCase() === 'PENDING' ||


        bill.billStatus.toUpperCase() === 'SNOOZED'


      )


  );


}
 


paidBills(): any[] {


  return this.bills.filter(

    bill =>

      bill.billStatus &&

      bill.billStatus.toUpperCase() === 'PAID'

  );


}


reminderBills(): any[] {


  return this.bills.filter(

    bill =>

      bill.reminderEnabled &&

      bill.reminderEnabled.toUpperCase() === 'YES'

  );


}


upcomingBills(): any[] {


  return this.bills.filter(

    bill =>

      bill.billStatus &&

      bill.billStatus.toUpperCase() === 'PENDING'

  );


}


billStatusClass(status: string): string {


  if (!status) {

    return 'pending-badge';

  }


  return status.toUpperCase() === 'PAID'

    ? 'paid-badge'

    : 'pending-badge';


}


viewOverview(): void {


  this.loadBills();


  this.selectedAction = 'OVERVIEW';


  this.showModal = true;


}


viewReminderSettings(): void {


  this.loadBills();


  this.selectedAction = 'REMINDER';


  this.showModal = true;


}


viewUpcomingBills(): void {


  this.loadBills();


  this.selectedAction = 'UPCOMING';


  this.showModal = true;


}


viewSnoozeBills(): void {


  this.loadBills();


  this.selectedAction = 'SNOOZE';


  this.showModal = true;


}


payNow(bill: any): void {


  if (!bill) {

    return;

  }


  bill.billStatus = 'PAID';


  this.billService

      .updateBill(

        bill.billId,

        bill

      )

      .subscribe({


        next: () => {


          alert(

            'Bill Paid Successfully'

          );


          this.loadBills();


        },


        error: (error) => {


          console.error(error);


        }


      });


}


refreshBills(): void {


  this.loadBills();


}


trackByBillId(

  index: number,

  bill: any

): number {


  return bill.billId;


}
 


}
 