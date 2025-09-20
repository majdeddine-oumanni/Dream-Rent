import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

interface Property{
  id: number,
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
export class FilteringService {
  private url = "http://localhost:8080/api/properties/search";

  constructor(private http: HttpClient) { }

  getPropertiesByType(type: string):Observable<Property[]>{
    return this.http.get<Property[]>(`${this.url}/type?type=${type}`);
  }
  getPropertiesByPrice(price1: number, price2:number):Observable<Property[]>{
    return this.http.get<Property[]>(`${this.url}?price1=${price1}&price2=${price2}`);
  }

  getPropertiesByPlace(place: string):Observable<Property[]>{
    return this.http.get<Property[]>(`${this.url}?place=${place}`);
  }
}
