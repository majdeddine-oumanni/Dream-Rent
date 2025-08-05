import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../Service/users.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../Service/auth.service';


interface User {
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  country: string,
  role: 'OWNER' | 'TENANT'
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
  // Helper method to check if a field has errors and should show them
  isFieldInvalid(fieldName: string): boolean {
    const field = this.registrationForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  // Helper method to check if a field is valid and should show success state
  isFieldValid(fieldName: string): boolean {
    const field = this.registrationForm.get(fieldName);
    return !!(field && field.valid && (field.dirty || field.touched));
  }

  // Helper method to get specific error for a field
  getFieldError(fieldName: string, errorType: string): boolean {
    const field = this.registrationForm.get(fieldName);
    return !!(field && field.errors && field.errors[errorType]);
  }

    onSubmit():void{
    if(this.registrationForm.valid){
      this.isSubmitted = true;

      // Mark all fields as touched to show any remaining validation errors
      this.markFormGroupTouched();

      try {
        this.service.register(this.registrationForm.value).subscribe((response)=>{
          console.log(response.firstName + " was added");
        })
        console.log('Form submitted:', this.registrationForm.value);

        // Success feedback
        alert("Registration successful!");

        // Reset form after successful submission
        this.registrationForm.reset();
        this.registrationForm.patchValue({ role: '' }); // Reset role to empty

      } catch (error) {
        console.error('Registration error:', error);
        alert("Registration failed. Please try again.");
      } finally {
        this.isSubmitted = false;
      }
    } else {
      // Mark all fields as touched to show validation errors
      this.markFormGroupTouched();
      console.log("Form is invalid");
    }
  }

    // Helper method to mark all form fields as touched
  private markFormGroupTouched(): void {
    Object.keys(this.registrationForm.controls).forEach(key => {
      const control = this.registrationForm.get(key);
      if (control) {
        control.markAsTouched();
      }
    });
  }

  // Method to reset form
  resetForm(): void {
    this.registrationForm.reset();
    this.registrationForm.patchValue({ role: '' });
  }
}
