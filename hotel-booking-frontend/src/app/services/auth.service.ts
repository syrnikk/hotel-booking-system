import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginRequest } from '../model/login-request.model';

const AUTH_API = 'http://localhost:8080/api/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, public router: Router) {}

  login(loginRequest: LoginRequest) {
    return this.http
      .post<any>('/api/auth/login', loginRequest)
      .subscribe((res: any) => {
        console.log(res.token)
        localStorage.setItem('access_token', res.token);
      });
  }

  getToken() {
    return localStorage.getItem('access_token');
  }
}
