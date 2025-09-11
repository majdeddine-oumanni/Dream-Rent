import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../Service/auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsersService } from '../../Service/users.service';

interface requiredData{
  firstName: string,
  lastName : string,
  email: string,
  country: string,
  phone : string
}
@Component({
  selector: 'app-profile',
  imports: [FormsModule, ReactiveFormsModule ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  updateFrom !: FormGroup;

  constructor(private service: AuthService, private fb : FormBuilder, private userService : UsersService) {
    this.updateFrom = fb.group({
      firstName : ['', [Validators.required, Validators.minLength(3)]],
      lastName : ['', [Validators.required, Validators.minLength(3)]],
      email : ['', [Validators.required, Validators.email]],
      country : ['', [Validators.required]],
      phone: ['']
    })
    this.updateFrom.disable();
  }

  onSubmit(){
    const data = this.updateFrom.value as requiredData;
    if(this.updateFrom.valid){
      localStorage.removeItem("auth_user");
      this.userService.updateUser(this.user.id, data).subscribe((response)=>{
        this.user = response;
        this.service.setUserData(this.user);
        console.log("updated data" + this.user);
      })
    }
    this.editMode = false;
    this.updated = true;
  }

  user: any;

  ngOnInit(): void {
    this.user = JSON.parse(this.service.getUserData());
    console.log(this.user);
    if(this.user){
      this.updateFrom.patchValue({
        firstName: this.user.firstName,
        lastName: this.user.lastName,
        email: this.user.email,
        country: this.user.country,
        phone: this.user.phone
      })
    }
  }

  editMode : boolean = false;

  toggleEditMode() {
    this.editMode = !this.editMode;
    if (this.editMode) {
      this.updateFrom.enable();
    } else {
      this.updateFrom.disable();
    }
  }

  cancelEdit() {
    this.editMode = false;
    this.updateFrom.disable();

    this.updateFrom.patchValue({
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email,
      country: this.user.country,
      phone: this.user.phone
    });
  }


  updated : boolean = false;

  updatedUser(){
    this.updated = true;
  }

  logout(){
    this.service.logout();
  }


}
