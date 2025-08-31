import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PropertiesListService } from '../../Service/properties-list.service';

@Component({
  selector: 'app-property-details',
  imports: [],
  templateUrl: './property-details.component.html',
  styleUrl: './property-details.component.css'
})
export class PropertyDetailsComponent implements OnInit{
  propertyId !: number;
  constructor(private route : ActivatedRoute, private service : PropertiesListService) {}
  property : any;
  owner : any;

  ngOnInit(): void {
    this.propertyId = Number(this.route.snapshot.paramMap.get('id'));
    this.service.retrievePropertyById(this.propertyId).subscribe((data)=>{
      this.property = data;
      console.log(this.property);
    })
    this.getPropertyOwner(this.propertyId);
  }

  //used for converting number of review to an array
  createRange(count: number): number[] {
    return Array.from({ length: Math.floor(count) }, (_, i) => i);
  }

  getPropertyOwner(property_id : number):any{
    this.service.getOwnerDataByPropertyId(property_id).subscribe((data)=>{
      this.owner = data;
      console.log(this.owner);
    })
  }

}
