import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../Service/auth.service';


interface User {
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  country: string,
  phone: string,
  role: 'ADMIN' | 'OWNER' | 'TENANT'
};
@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
  registrationForm !: FormGroup;
  isSubmitted = false;
  constructor(private fb: FormBuilder, private service: AuthService){}
  ngOnInit(): void {
      this.registrationForm = this.fb.group({
        firstName : ['', [Validators.required, Validators.minLength(3)]],
        lastName : ['', [Validators.required, Validators.minLength(3)]],
        email : ['', [Validators.required, Validators.email]],
        password : ['', [Validators.required , Validators.minLength(8)]],
        role : ['', [Validators.required]],
        country : ['', [Validators.required]]
      })
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.registrationForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }


  isFieldValid(fieldName: string): boolean {
    const field = this.registrationForm.get(fieldName);
    return !!(field && field.valid && (field.dirty || field.touched));
  }

  getFieldError(fieldName: string, errorType: string): boolean {
    const field = this.registrationForm.get(fieldName);
    return !!(field && field.errors && field.errors[errorType]);
  }

    onSubmit():void{
    if(this.registrationForm.valid){
      this.isSubmitted = true;

      this.markFormGroupTouched();
      const payload = this.registrationForm.value as User;

      try {
        this.service.register(payload).subscribe((response)=>{
          console.log(response.firstName + " was added");
        })
        console.log('Form submitted:', this.registrationForm.value);

        alert("Registration successful!");

        this.registrationForm.reset();
        this.registrationForm.patchValue({ role: '' });

      } catch (error) {
        console.error('Registration error:', error);
        alert("Registration failed. Please try again.");
      } finally {
        this.isSubmitted = false;
      }
    } else {
      this.markFormGroupTouched();
      console.log("Form is invalid");
    }
  }

  private markFormGroupTouched(): void {
    Object.keys(this.registrationForm.controls).forEach(key => {
      const control = this.registrationForm.get(key);
      if (control) {
        control.markAsTouched();
      }
    });
  }

  resetForm(): void {
    this.registrationForm.reset();
    this.registrationForm.patchValue({ role: '' });
  }
}
