import { Component, OnInit } from '@angular/core';
import { PropertiesListService } from '../../Service/properties-list.service';
import { RouterLink } from "@angular/router";

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

@Component({
  selector: 'app-owners-properties',
  imports: [RouterLink],
  templateUrl: './owners-properties.component.html',
  styleUrl: './owners-properties.component.css'
})
export class OwnersPropertiesComponent implements OnInit{
  constructor(private service : PropertiesListService) {}
  propertiesNumber !: number;
  availableProperties !: number;
  totalReservations !: number;
  propertiesList !: Property[];
  propertyReservations: { [key: number]: number } = {};


  ngOnInit(): void {
    const data = localStorage.getItem("auth_user") || "";
    const user = JSON.parse(data);
    this.getPropertiesNumber(user.id);
    this.getAvailableProperties(user.id);
    this.getTotalReservations(user.id);
    this.getOwnersPropertiesList(user.id);

  }


  getPropertiesNumber(ownerId : number){
    this.service.getOwnersPropertiesNumber(ownerId).subscribe((number)=>{
      this.propertiesNumber = number;
    })
  }

  getAvailableProperties(ownerId : number){
    this.service.getOwnersAvailablePropertiesNumber(ownerId).subscribe((number)=>{
      this.availableProperties = number;
    })
  }

  getTotalReservations(userId : number){
    this.service.getReservationsNumberByOwner(userId).subscribe((total)=>{
      this.totalReservations = total;
    })
  }

  getOwnersPropertiesList(ownerId : number){
    this.service.getOwnerProperties(ownerId).subscribe((data)=>{
      this.propertiesList = data;
      this.propertiesList.forEach((property) => {
        this.getPropertiesReservationNumber(property.id);
      });
    })
  }

  getPropertiesReservationNumber(propertyId : number){
    this.service.propertiesReservationNumber(propertyId).subscribe((total)=>{
      this.propertyReservations[propertyId] = total;
    })
  }

  deleteProperty(propertyId : number){
    this.service.deleteProperty(propertyId).subscribe(()=>{
      this.propertiesList = this.propertiesList.filter(property => property.id != propertyId);

      delete this.propertyReservations[propertyId];
    })
  }



}
