import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, of } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  showValidationError: boolean = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: [''],
      password: ['']
    });
  }

  onSubmit() {
    const observer = {
      next: (res: any) => {
        localStorage.setItem('access_token', res.token);
        this.authService.isLoggedInSource.next(true);
        this.authService.userSource.next(res.userDto)
        this.router.navigate(['/']);
      },
      error: (error: any) => { 
        this.showValidationError = true;
      }
    };

    this.authService.login(this.loginForm.value).subscribe(observer);
  }
}
