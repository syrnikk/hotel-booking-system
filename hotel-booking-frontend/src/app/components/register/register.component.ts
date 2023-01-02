import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.fb.group({
      name: [''],
      lastName: [''],
      email: [''],
      password: [''],
      phone: ['']
    });
  }

  onSubmit() {
    console.log(this.registerForm.value)
    this.authService.register(this.registerForm.value).subscribe(data => {
      console.log(data.message);
      this.router.navigate(['/login']);
    })

  }
}
