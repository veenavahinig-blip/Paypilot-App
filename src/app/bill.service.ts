import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';


@Injectable({

  providedIn: 'root'

})

export class BillService {


  private apiUrl = 'http://localhost:8086/api/bills';
  private apiUrlPayments ='http://localhost:8086/api/payments';
 


  constructor(private http: HttpClient) { }


  addBill(data: any) {
  return this.http.post(
    `${this.apiUrl}/add`,
    data,
    {
      responseType: 'text'
    }
  );
 
 
}
 
 


  getUserBills(userId: string) {


  return this.http.get<any[]>(

    `${this.apiUrl}/user/${userId}`

  );


}
 


  setReminder(id: number): Observable<any> {

    return this.http.put(`${this.apiUrl}/set-reminder/${id}`, {});

  }


  snoozeBill(id: number): Observable<any> {


  return this.http.put(

    `${this.apiUrl}/snooze/${id}`,

    {},

    {

      responseType: 'text'

    }

  );


}


unSnoozeBill(id: number): Observable<any> {


  return this.http.put(

    `${this.apiUrl}/unsnooze/${id}`,

    {},

    {

      responseType: 'text'

    }

  );


}
 


  updateBill(id: number, bill: any): Observable<any> {

    return this.http.put(`${this.apiUrl}/update/${id}`, bill);

  }

  payBill(

  userId: String,

  billId: number

) {


  return this.http.post(

    `${this.apiUrlPayments}/pay/${userId}/${billId}`,

    {},

    {

      responseType: 'text'

    }

  );


}

getPendingBills(userId: string) {

  return this.http.get<any[]>(

    `${this.apiUrl}/pending/${userId}`

  );

}


enableSchedulePayment(billId: number) {

  this.http.put(

  `${this.apiUrl}/schedule/${billId}`,

  {},

  {

    responseType: 'text'

  }

)

.subscribe({

  next: (response) => {

    console.log(response);

  },

  error: (error) => {

    console.error(error);

  }

});

 
 

}


autoPayBills() {

  return this.http.post(

    `${this.apiUrl}/autopay`,

    {}

  );

}

enableAutoPay(billId: number) {

  return this.http.put(

    `${this.apiUrl}/schedule/${billId}`,

    {},

    { responseType: 'text' }

  );

}


disableAutoPay(billId: number) {

  return this.http.put(

    `${this.apiUrl}/unschedule/${billId}`,

    {},

    { responseType: 'text' }

  );

}
 
 
 

}
 