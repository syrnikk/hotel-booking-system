import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  showValidationError: boolean = false;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.fb.group({
      name: [null, Validators.required],
      lastName: [null, Validators.required],
      email: [null, Validators.required],
      password: [null, Validators.required],
      phone: [null]
    });
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data.message);
        this.router.navigate(['/login']);
      },
      error: (error: any) => { 
        this.showValidationError = true;
      }
    };

    console.log(this.registerForm.value)
    this.authService.register(this.registerForm.value).subscribe(observer);
  }
}
