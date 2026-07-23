import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';


@Injectable({

  providedIn: 'root'

})

export class ProfileService {


  private baseUrl = 'http://localhost:8086/users';


  constructor(private http: HttpClient) { }


  getProfile(email: string): Observable<any> {


    return this.http.get(

      this.baseUrl + '/profile/' + email

    );


  }


  updateProfile(

    email: string,

    user: any

  ): Observable<any> {


    return this.http.put(

      this.baseUrl + '/profile/' + email,

      user

    );


  }


}
 