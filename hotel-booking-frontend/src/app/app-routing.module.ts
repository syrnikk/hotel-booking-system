import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './components/home/home.component';
import { HotelComponent } from './components/hotel/hotel.component';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RoomBookingComponent } from './components/room-booking/room-booking.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { AuthGuard } from './auth/auth.guard';
import { AdminMainComponent } from './components/admin/admin-main/admin-main.component';
import { AdminCountryComponent } from './components/admin/country/admin-country/admin-country.component';
import { AdminAddCountryComponent } from './components/admin/country/admin-add-country/admin-add-country.component';
import { AdminEditCountryComponent } from './components/admin/country/admin-edit-country/admin-edit-country.component';
import { AdminCityComponent } from './components/admin/city/admin-city/admin-city.component';
import { AdminAddCityComponent } from './components/admin/city/admin-add-city/admin-add-city.component';
import { AdminAddressComponent } from './components/admin/address/admin-address/admin-address.component';
import { AdminAddAddressComponent } from './components/admin/address/admin-add-address/admin-add-address.component';

const routes: Routes = [
  { path: '', component: HomeComponent, },
  { path: 'login', component: LoginComponent, },
  { path: 'register', component: RegisterComponent },
  { path: 'hotel', component: HotelComponent, canActivate: [AuthGuard] },
  { path: 'hotel/:id', component: RoomBookingComponent, canActivate: [AuthGuard] },
  { path: 'reservation', component: ReservationsComponent, canActivate: [AuthGuard] },
  { path: 'admin', component: AdminMainComponent, canActivate: [AuthGuard], children: [
    { path: 'country', component: AdminCountryComponent, canActivate: [AuthGuard] },
    { path: 'add-country', component: AdminAddCountryComponent, canActivate: [AuthGuard] },
    { path: 'edit-country', component: AdminEditCountryComponent, canActivate: [AuthGuard] },
    { path: 'city', component: AdminCityComponent, canActivate: [AuthGuard] },
    { path: 'add-city', component: AdminAddCityComponent, canActivate: [AuthGuard] },
    { path: 'address', component: AdminAddressComponent, canActivate: [AuthGuard] },
    { path: 'add-address', component: AdminAddAddressComponent, canActivate: [AuthGuard] }
  ]}
];

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
