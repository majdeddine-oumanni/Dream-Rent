import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../Service/auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

interface requiredData{
  firstName: string,
  lastName: string,
  country: string,
  phone: string,
  email: string
}
@Component({
  selector: 'app-profile',
  imports: [FormsModule, ReactiveFormsModule ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  updateFrom !: FormGroup;

  constructor(private service: AuthService, private fb : FormBuilder) {
    this.updateFrom = fb.group({
      firstName : ['', [Validators.required, Validators.minLength(3)]],
      lastName : ['', [Validators.required, Validators.minLength(3)]],
      email : ['', [Validators.required, Validators.email]],
      country : ['', [Validators.required]]
    })
  }

  onSubmit(){
    const data = this.updateFrom.value as requiredData;
    if(this.updateFrom.valid){

    }
    this.editMode = false;
  }

  user: any;

  ngOnInit(): void {
    this.user = JSON.parse(this.service.getUserData());
    console.log(this.user);
  }

  editMode : boolean = false;
  toggleEditMode() {
    this.editMode = !this.editMode;
  }

  cancelEdit() {
    this.editMode = false;
  }

  updated : boolean = false;

  updatedUser(){
    this.updated = true;
  }

}
