import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../../Service/auth.service';

interface requiredData{
  email: string,
  password: string
}

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm !: FormGroup;
  constructor(private fb : FormBuilder, private service: AuthService){
    this.loginForm = fb.group({
      email : ['', [Validators.required, Validators.email]],
      password : ['', [Validators.required, Validators.minLength(8)]]
    })
  }
  onSubmit(){
    const data = this.loginForm.value as requiredData;
    if (this.loginForm.valid) {
    //console.log('Form Submitted', this.loginForm.value);
    this.service.login(data).subscribe((response)=>{
      console.log(response);
    })
    }
  }
}
