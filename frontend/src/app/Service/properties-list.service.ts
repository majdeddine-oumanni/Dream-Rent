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
  avrgReview : number,
  features : []
}
interface Owner{
  firstName: string,
  lastName: string,
  email: string,
  country: string,
  phone: string
}
@Injectable({
  providedIn: 'root'
})

export class PropertiesListService {

  private baseUrl : string = "http://localhost:8080/api/properties";
  private reservatioUrl : string = "http://localhost:8080/api/reservation";

  constructor(private http: HttpClient) { }

  retrieveAllProperties():Observable<Property[]>{
    return this.http.get<Property[]>(this.baseUrl);
  }

  retrievePropertyById(id:number):Observable<Property>{
    return this.http.get<Property>(`${this.baseUrl}/${id}`);
  }

  getOwnerDataByPropertyId(id:number):Observable<Owner>{
    return this.http.get<Owner>(`${this.baseUrl}/ownerOfProperty/${id}`);
  }

  deleteProperty(id : number):Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getOwnersPropertiesNumber(ownerId : number):Observable<number>{
    return this.http.get<number>(`${this.baseUrl}/ownersPropertiesNumber/${ownerId}`);
  }

  getOwnersAvailablePropertiesNumber(ownerId : number):Observable<number>{
    return this.http.get<number>(`${this.baseUrl}/availableProperties/${ownerId}`);
  }

  getReservationsNumberByOwner(ownerId : number): Observable<number>{
    return this.http.get<number>(`${this.reservatioUrl}/total/${ownerId}`);
  }

  getOwnerProperties(ownerId : number): Observable<Property[]>{
    return this.http.get<Property[]>(`${this.baseUrl}/owner/${ownerId}`);
  }

  propertiesReservationNumber(propertyId : number): Observable<number>{
    return this.http.get<number>(`${this.reservatioUrl}/totalByProperty/${propertyId}`);
  }

}
