import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { PropertiesListService } from '../../Service/properties-list.service';
import { ActivatedRoute } from '@angular/router';

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
  selector: 'app-property-update',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './property-update.component.html',
  styleUrl: './property-update.component.css'
})
export class PropertyUpdateComponent implements OnInit{
  propertyForm !: FormGroup;

  constructor(private fb: FormBuilder, private service : PropertiesListService, private route: ActivatedRoute) {
    this.propertyForm = fb.group({
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      country: ['', [Validators.required]],
      city: ['', [Validators.required]],
      propertyType: ['', [Validators.required]],
      roomsNumber: [0, [Validators.required, Validators.max(20), Validators.min(1)]],
      bathroomsNumber: [0, [Validators.required, Validators.max(10), Validators.min(1)]],
      price: [0, [Validators.required]],
      guests: [0, [Validators.required, Validators.max(50), Validators.min(1)]],
      area: [0, [Validators.required, Validators.min(3)]],
      features: this.fb.array([])
    })
  }

  propertyId !: number;
  property !: Property;
  ngOnInit(): void {
    this.propertyId = Number(this.route.snapshot.paramMap.get('id'));
    this.service.retrievePropertyById(this.propertyId).subscribe((data)=>{
      this.property = data;
      console.log(this.property);
      if(this.property){
        this.propertyForm.patchValue({
          title : this.property.title,
          description: this.property.description,
          country: this.property.country,
          city : this.property.city,
          propertyType : this.property.propertyType,
          roomsNumber : this.property.roomsNumber,
          bathroomsNumber : this.property.bathroomsNumber,
          price: this.property.price,
          guests : this.property.guests,
          area : this.property.area
        })
      }
    })

  }

  submitted : boolean = false;
  onSubmit(){
    if(this.propertyForm.valid){
      const property = this.propertyForm.value;
      this.service.updateProperty(this.property.id, property).subscribe({
        next: (response)=>{
          this.submitted = true;
        }
      })
    }else{
      alert("the form is not valid");
    }
  }

  onCheckboxChange(event: any) {
    const features: FormArray = this.propertyForm.get('features') as FormArray;

    if (event.target.checked) {
      features.push(this.fb.control(event.target.value));
    } else {
      const index = features.controls.findIndex(x => x.value === event.target.value);
      features.removeAt(index);
    }
  }

}
