import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn: boolean = this.authService.isLoggedIn;
  user: any = this.authService.user;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.isLoggedIn$.subscribe(data => {
      this.isLoggedIn = data;
    });

    this.authService.user$.subscribe(data => {
      this.user = data;
    });
  }

  logout() {
    this.authService.logout();
  }

  isAdmin() {
    if(this.user == null) {
      return false;
    }
    return this.user.roles.includes("ROLE_ADMIN");
  }
}
