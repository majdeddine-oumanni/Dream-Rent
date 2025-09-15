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

  constructor(private http: HttpClient) { }

  getPropertiesByType(type: string):Observable<Property[]>{
    return this.http.get<Property[]>(`http://localhost:8080/api/properties/search/type?type=${type}`)
  }
}
