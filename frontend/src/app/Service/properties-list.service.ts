import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


interface Property{
  title: string,
  description: string,
  country: string,
  city: string,
  roomsNumber: number,
  area: number,
  bathroomsNumber: number,
  availability: boolean,
  price: number,
  guests: number,
  //images: [],
  propertyType: 'APARTMENT' | 'VILLA' | 'HOUSE',
  avrgReview : number
}
@Injectable({
  providedIn: 'root'
})

export class PropertiesListService {

  baseUrl : string = "http://localhost:8080/api/properties";

  constructor(private http: HttpClient) { }

  retrieveAllProperties():Observable<Property[]>{
    return this.http.get<Property[]>(this.baseUrl);
  }

}
