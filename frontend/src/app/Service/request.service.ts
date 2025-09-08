import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface Reservation{
  startDate: string,
  endDate: string,
  property_id : number
}
@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private url = "http://localhost:8080/reservation";
  constructor(private http: HttpClient) { }

  sendRequest(reservation : Reservation): Observable<Reservation>{
    return this.http.post<Reservation>(this.url, reservation);
  }

}
