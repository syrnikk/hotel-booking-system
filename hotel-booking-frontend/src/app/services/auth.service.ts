import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginRequest } from '../model/login-request.model';
import { RegisterRequest } from '../model/register-request.model';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, public router: Router) {}

  login(loginRequest: LoginRequest) {
    this.http
      .post<any>('/api/auth/login', loginRequest)
      .subscribe((res: any) => {
        console.log(res.token)
        localStorage.setItem('access_token', res.token);
      });
  }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http
      .post<any>('/api/auth/register', registerRequest);
  }

  getToken() {
    return localStorage.getItem('access_token');
  }
}
