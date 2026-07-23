import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
 
@Injectable({
  providedIn: 'root'
})
export class NotificationService {
 
  private baseUrl = 'http://localhost:8086/notification';
 
  constructor(private http: HttpClient) { }
 
  getNotifications(userId: String): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${userId}`);
  }
 
  markAsRead(notificationId: number): Observable<any> {
 
  return this.http.put(`${this.baseUrl}/read/${notificationId}`,{},{ responseType: 'text' }
  );
 
}
 
}
 