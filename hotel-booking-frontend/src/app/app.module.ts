import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HotelDetailsComponent } from './components/hotel-details/hotel-details.component';
import { RoomBookingComponent } from './components/room-booking/room-booking.component';
import { AuthInterceptor } from './auth/auth.interceptor';
import { RoomDetailsComponent } from './components/room-details/room-details.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { ReservationDetailsComponent } from './components/reservation-details/reservation-details.component';
import { AdminCountryComponent } from './components/admin/country/admin-country/admin-country.component';
import { AdminAddCountryComponent } from './components/admin/country/admin-add-country/admin-add-country.component';
import { AdminEditCountryComponent } from './components/admin/country/admin-edit-country/admin-edit-country.component';
import { AdminMainComponent } from './components/admin/admin-main/admin-main.component';
import { AdminSidebarComponent } from './components/admin/admin-sidebar/admin-sidebar.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HotelComponent,
    LoginComponent,
    RegisterComponent,
    HotelDetailsComponent,
    RoomBookingComponent,
    RoomDetailsComponent,
    ReservationsComponent,
    ReservationDetailsComponent,
    AdminCountryComponent,
    AdminAddCountryComponent,
    AdminEditCountryComponent,
    AdminMainComponent,
    AdminSidebarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
