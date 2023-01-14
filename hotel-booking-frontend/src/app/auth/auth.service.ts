import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginRequest } from '../model/login-request.model';
import { RegisterRequest } from '../model/register-request.model';
import { Observable, Subject } from 'rxjs';
import jwtDecode from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedInSource = new Subject<boolean>();
  isLoggedIn$ = this.isLoggedInSource.asObservable();

  userSource = new Subject<any>();
  user$ = this.userSource.asObservable();

  constructor(private http: HttpClient, public router: Router) {}

  login(loginRequest: LoginRequest) {
    return this.http
      .post<any>('/api/auth/login', loginRequest);
  }

  register(registerRequest: RegisterRequest): Observable<any> {
    return this.http
      .post<any>('/api/auth/register', registerRequest);
  }

  logout() {
    let removeToken = localStorage.removeItem('access_token');
    localStorage.removeItem('user');
    if (removeToken == null) {
      this.isLoggedInSource.next(false);
      this.userSource.next(null);
      this.router.navigate(['login']);
    }
  }

  getToken() {
    return localStorage.getItem('access_token');
  }

  get isLoggedIn(): boolean {
    const accessToken = localStorage.getItem('access_token');
    if(accessToken == null) {
      return false;
    } else {
      const decodedToken: any = jwtDecode(accessToken);
      const exp = decodedToken.exp * 1000;
      const now = Date.now();
      return exp > now;
    }
  }

  get user(): any {
    const userData = localStorage.getItem('user');
    if (userData != null) {
      return JSON.parse(userData);
    }
    return null;
  }
}
