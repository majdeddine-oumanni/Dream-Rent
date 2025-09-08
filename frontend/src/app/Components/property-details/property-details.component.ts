import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PropertiesListService } from '../../Service/properties-list.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RequestService } from '../../Service/request.service';

interface Reservation{
  startDate: string,
  endDate: string,
  property_id : number
}
@Component({
  selector: 'app-property-details',
  imports: [ReactiveFormsModule],
  templateUrl: './property-details.component.html',
  styleUrl: './property-details.component.css'
})
export class PropertyDetailsComponent implements OnInit{
  propertyId !: number;
  reservation !: FormGroup;
  constructor(private route : ActivatedRoute, private service : PropertiesListService, private fb : FormBuilder, private request : RequestService) {
    this.reservation = fb.group({
      startDate : ['', [Validators.required]],
      endDate : ['', [Validators.required]],
      property_id : ['', [Validators.required]]
    })
  }
  property : any;
  owner : any;

  ngOnInit(): void {
    this.propertyId = Number(this.route.snapshot.paramMap.get('id'));
    this.service.retrievePropertyById(this.propertyId).subscribe((data)=>{
      this.property = data;
      this.reservation.patchValue({property_id : this.property.id});
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

  onSubmit(){
    const data = this.reservation.value as Reservation;
    if(this.reservation.valid){
      this.request.sendRequest(data).subscribe((response)=>{
        console.log(response);
      })
    }
  }

}
