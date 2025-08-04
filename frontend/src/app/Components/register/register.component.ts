import { Component } from '@angular/core';
import { UsersService } from '../../Service/users.service';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';

interface User {
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  role: string,
  country: string
};
@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private service: UsersService){}
}
