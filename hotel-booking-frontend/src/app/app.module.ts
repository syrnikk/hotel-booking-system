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
import { AdminCityComponent } from './components/admin/city/admin-city/admin-city.component';
import { AdminAddCityComponent } from './components/admin/city/admin-add-city/admin-add-city.component';
import { AdminAddressComponent } from './components/admin/address/admin-address/admin-address.component';
import { AdminAddAddressComponent } from './components/admin/address/admin-add-address/admin-add-address.component';
import { AdminHotelComponent } from './components/admin/hotel/admin-hotel/admin-hotel.component';
import { AdminAddHotelComponent } from './components/admin/hotel/admin-add-hotel/admin-add-hotel.component';
import { AdminRoomTypeComponent } from './components/admin/room-type/admin-room-type/admin-room-type.component';
import { AdminAddRoomTypeComponent } from './components/admin/room-type/admin-add-room-type/admin-add-room-type.component';
import { AdminRoomComponent } from './components/admin/room/admin-room/admin-room.component';
import { AdminAddRoomComponent } from './components/admin/room/admin-add-room/admin-add-room.component';


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
    AdminSidebarComponent,
    AdminCityComponent,
    AdminAddCityComponent,
    AdminAddressComponent,
    AdminAddAddressComponent,
    AdminHotelComponent,
    AdminAddHotelComponent,
    AdminRoomTypeComponent,
    AdminAddRoomTypeComponent,
    AdminRoomComponent,
    AdminAddRoomComponent
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
