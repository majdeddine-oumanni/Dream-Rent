import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersService } from '../../Service/users.service';
import { FormBuilder, FormGroup, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from "@angular/router";

interface User {
  id: number,
  firstName: string,
  lastName : string,
  email: string,
  role : string,
  country: string,
  phone : string,
  isBanned : boolean
};

@Component({
  selector: 'app-update-user',
  imports: [FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './update-user.component.html',
  styleUrl: './update-user.component.css'
})
export class UpdateUserComponent implements OnInit{
  userData !: FormGroup;
  constructor(private route: ActivatedRoute, private service: UsersService, private fb: FormBuilder) {
    this.userData = fb.group({
      firstName : ['', [Validators.required, Validators.minLength(3)]],
      lastName : ['', [Validators.required, Validators.minLength(3)]],
      email : ['', [Validators.required, Validators.email]],
      role : [''],
      country : ['', [Validators.required]],
      phone: ['']
    })
  }
  userId !: number;
  user !: User;

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get('id'));
    console.log(this.userId);
    this.getUserData(this.userId);

  }


  onSubmit( id: number){
    const data = this.userData.value as User;
    if(this.userData.valid){
      window.scrollTo({top: 0, behavior: "smooth"})
      this.service.updateUserByAdmin(id, data).subscribe((response)=>{
        this.user = response;
        console.log(response);
      })
    }
  }

  getUserData(id: number){
    this.service.getUserById(id).subscribe((data)=>{
      this.user = data;
      console.log(this.user);


      this.userData.patchValue({
        firstName : this.user.firstName,
        lastName : this.user.lastName,
        email: this.user.email,
        role : this.user.role,
        country: this.user.country,
        phone: this.user.phone
      })
    })
  }

  updated : boolean = false;
  update(){
    this.updated = !this.updated;
  }
}
