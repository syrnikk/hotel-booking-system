import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService, private router: Router) {} 

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {  
    const token = this.authService.getToken();
    if (request.url == '/api/auth/login') {
      return next.handle(request);
    }
    if (token) {
     request = request.clone({
        setHeaders: {Authorization: `Bearer ${token}`}
     });
    }
    this.authService.isLoggedInSource.next(this.authService.isLoggedIn)

  return next.handle(request).pipe(
  	catchError((err) => {
   	 if (err instanceof HttpErrorResponse) {
       	 if (err.status === 401) {
          this.router.navigate(['login']);
        }
 	 }
  	return throwError(() => err);
	})
   )
  }
}
