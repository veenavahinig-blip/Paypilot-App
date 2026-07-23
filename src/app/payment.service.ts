import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';


@Injectable({

  providedIn: 'root'

})

export class PaymentService {


  private apiUrl =

    'http://localhost:8086/api/payments';


  constructor(

    private http: HttpClient

  ) { }


  getPaymentHistory(

    userId: string

  ): Observable<any[]> {


    return this.http.get<any[]>(

      `${this.apiUrl}/history/${userId}`

    );


  }


}
 