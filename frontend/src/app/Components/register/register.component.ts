import { Component } from '@angular/core';
import { UsersService } from '../../Service/users.service';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private service: UsersService){}
user = {
  firstName: '',
  lastName: '',
  email: '',
  password: '',
  role: '',
  country: ''
};
  onRegister(){
    this.service.addUser(this.user).subscribe((response:any)=>{
      console.log(response, this.user);
    })
  }
  countries:any[]=[]
  getCountries(){
    return this.service.restoureCountries().subscribe((data:any)=>{
      this.countries = data;
    })
  }
}
