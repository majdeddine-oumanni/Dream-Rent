import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { PropertiesListService } from '../../Service/properties-list.service';

@Component({
  selector: 'app-property-update',
  imports: [ReactiveFormsModule, FormsModule],
  templateUrl: './property-update.component.html',
  styleUrl: './property-update.component.css'
})
export class PropertyUpdateComponent {
    propertyForm !: FormGroup;

  constructor(private fb: FormBuilder, private service : PropertiesListService) {
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

  submitted : boolean = false;
  onSubmit(){
    if(this.propertyForm.valid){
      const property = this.propertyForm.value;
      this.service.postProperty(property).subscribe({
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
