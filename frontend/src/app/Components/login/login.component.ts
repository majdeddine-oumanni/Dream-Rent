import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { AuthService } from '../../Service/auth.service';
import { Router } from '@angular/router';

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
  constructor(private fb : FormBuilder, private service: AuthService, private router: Router){
    this.loginForm = fb.group({
      email : [''],
      password : ['']
    })
  }
  onSubmit(){
    const data = this.loginForm.value as requiredData;
    if (this.loginForm.valid) {
      this.service.login(data).subscribe((response)=>{
        this.service.setUserData(response);
        this.routing();
      })
    }
  }
  routing(){
    if(this.service.isLoggedIn()){
      this.router.navigate(['/']);
    }
  }
}
