import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface Payload{
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  role: 'OWNER' | 'TENANT',
  country: string
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http : HttpClient) { }
  private baseUrl = "http://localhost:8080/api/v1/auth"

  public register(user: Payload):Observable<Payload>{
    return this.http.post<Payload>(`${this.baseUrl}/register`, user);
  }
}
