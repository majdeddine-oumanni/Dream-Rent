import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { count, Observable } from 'rxjs';
interface User{
  firstName: string,
  lastName : string,
  email: string,
  password: string,
  role: string,
  country: string
}

interface Country{
    name: {
      common: string,
      official: string,
      nativeName: {
        fra: {
          official: string,
          common: string
        }
      }
    },
    idd: {
      root: string,
      suffixes: []
    }
}

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private http : HttpClient){}

  private url = "http://localhost:8080/user";
  private countriesURL = "https://restcountries.com/v3.1/all?fields=name,idd";

  getUsers():Observable<User[]>{
    return this.http.get<User[]>(this.url);
  }

  addUser(user:User):Observable<User>{
    return this.http.post<User>(`${this.url}/post`, user);
  }

  restoureCountries():Observable<Country[]>{
    return this.http.get<Country[]>(this.countriesURL);
  }

}
