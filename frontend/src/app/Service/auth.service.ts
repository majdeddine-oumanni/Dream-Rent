import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

interface Payload{
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  country: string,
  role: 'ADMIN' | 'OWNER' | 'TENANT',
  phone : string
}
interface Credentials{
  email: string,
  password : string
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey = 'auth_token';
  private userKey = 'auth_user';

  constructor(private http : HttpClient) { }
  private baseUrl = "http://localhost:8080/api/v1/auth"

  public register(user: Payload):Observable<Payload>{
    return this.http.post<Payload>(`${this.baseUrl}/register`, user);
  }


  // public login(user: LoginData):Observable<LoginData>{
  //   return this.http.post<LoginData>(`${this.baseUrl}/authenticate`, user)
  // }

  public  login(credentials: Credentials): Observable<Credentials> {
    return this.http.post<Credentials>(`${this.baseUrl}/authenticate`, credentials).pipe(
      tap((response: any) => {
        if (response.token) {
          this.setAuthData(response.token);
        }
      })
    );
  }
  private setAuthData(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  setUserData(user:any):void{
    localStorage.setItem('user', JSON.stringify(user));
  }

  getUserData():any{
    return localStorage.getItem('user');
  }


  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  isLoggedIn(): boolean {
    const t = this.getToken();
    return !!t;
  }
}
