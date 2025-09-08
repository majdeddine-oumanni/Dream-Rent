import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from "@angular/router";
import { PropertiesListService } from '../../Service/properties-list.service';
import { AuthService } from '../../Service/auth.service';

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
@Component({
  selector: 'app-home',
  imports: [CommonModule, RouterLink, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  constructor(private service : PropertiesListService, public authService: AuthService) {}

  properties !: Property[];
  ngOnInit(): void {
    this.service.retrieveAllProperties().subscribe((data)=>{
      this.properties = data;
      console.log(this.properties);
    })
  }
  createRange(count: number): number[] {
    return Array.from({ length: Math.floor(count) }, (_, i) => i);
  }

  deleteProperty(id : number){
    const confirmed = confirm("Are you sure you want to delete this property ?")
    if(confirmed){
      this.service.deleteProperty(id).subscribe(()=>{
      this.properties =  this.properties.filter(propertie => propertie.id != id);
      })
    }
  }

}
