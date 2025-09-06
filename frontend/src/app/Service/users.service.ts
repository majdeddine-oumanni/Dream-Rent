import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { count, Observable } from 'rxjs';

interface User{
  firstName: string,
  lastName : string,
  email: string,
  country: string,
  phone : string
}

interface Users{
  id:number,
  firstName: string,
  lastName : string,
  email: string,
  role : string,
  country: string,
  phone : string
}

interface UpdateUser{
  id: number,
  firstName: string,
  lastName : string,
  email: string,
  role : string,
  country: string,
  phone : string,
  isBanned : boolean
}


@Injectable({
  providedIn: 'root'
})
export class UsersService {
  constructor(private http : HttpClient){}

  private url = "http://localhost:8080/user";
  //private countriesURL = "https://restcountries.com/v3.1/all?fields=name,idd";
  // restoureCountries():Observable<any[]>{
  //   return this.http.get<any[]>(this.countriesURL);
  // }

  updateUser(id: number, user: User):Observable<User>{
    return this.http.put<User>(`${this.url}/update/${id}`, user);
  }

  updateUserByAdmin(id: number, user: UpdateUser):Observable<UpdateUser>{
    return this.http.put<UpdateUser>(`${this.url}/update/${id}`, user);
  }

  getUsers():Observable<Users[]>{
    return this.http.get<Users[]>(this.url);
  }

  deleteUser(id:number):Observable<void>{
    return this.http.delete<void>(`${this.url}/delete/${id}`);
  }

  getUsersNumber():Observable<number>{
    return this.http.get<number>(`${this.url}/totalNumber`);
  }

  getUsersNumberByRole(role:string):Observable<number>{
    return this.http.get<number>(`${this.url}/numberByRole?role=${role}`);
  }

  getUserById(id:number):Observable<UpdateUser>{
    return this.http.get<UpdateUser>(`${this.url}/${id}`);
  }

}
