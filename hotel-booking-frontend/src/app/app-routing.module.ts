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
import { AdminCityComponent } from './components/admin/city/admin-city/admin-city.component';
import { AdminAddCityComponent } from './components/admin/city/admin-add-city/admin-add-city.component';
import { AdminHotelComponent } from './components/admin/hotel/admin-hotel/admin-hotel.component';
import { AdminAddHotelComponent } from './components/admin/hotel/admin-add-hotel/admin-add-hotel.component';
import { AdminRoomTypeComponent } from './components/admin/room-type/admin-room-type/admin-room-type.component';
import { AdminAddRoomTypeComponent } from './components/admin/room-type/admin-add-room-type/admin-add-room-type.component';
import { AdminRoomComponent } from './components/admin/room/admin-room/admin-room.component';
import { AdminAddRoomComponent } from './components/admin/room/admin-add-room/admin-add-room.component';

const routes: Routes = [
  { path: '', component: HomeComponent, },
  { path: 'login', component: LoginComponent, },
  { path: 'register', component: RegisterComponent },
  { path: 'hotel', component: HotelComponent, canActivate: [AuthGuard] },
  { path: 'hotel/:id', component: RoomBookingComponent, canActivate: [AuthGuard] },
  { path: 'reservation', component: ReservationsComponent, canActivate: [AuthGuard] },
  { path: 'admin', component: AdminMainComponent, canActivate: [AuthGuard], children: [
    { path: '',   redirectTo: 'country', pathMatch: 'full' },    
    { path: 'country', component: AdminCountryComponent, canActivate: [AuthGuard] },
    { path: 'add-country', component: AdminAddCountryComponent, canActivate: [AuthGuard] },
    { path: 'edit-country/:id', component: AdminAddCountryComponent, canActivate: [AuthGuard] },
    { path: 'city', component: AdminCityComponent, canActivate: [AuthGuard] },
    { path: 'add-city', component: AdminAddCityComponent, canActivate: [AuthGuard] },
    { path: 'edit-city/:id', component: AdminAddCityComponent, canActivate: [AuthGuard] },
    { path: 'hotel', component: AdminHotelComponent, canActivate: [AuthGuard] },
    { path: 'add-hotel', component: AdminAddHotelComponent, canActivate: [AuthGuard] },
    { path: 'edit-hotel/:id', component: AdminAddHotelComponent, canActivate: [AuthGuard] },
    { path: 'room-type', component: AdminRoomTypeComponent, canActivate: [AuthGuard] },
    { path: 'add-room-type', component: AdminAddRoomTypeComponent, canActivate: [AuthGuard] },
    { path: 'edit-room-type/:id', component: AdminAddRoomTypeComponent, canActivate: [AuthGuard] },
    { path: 'room', component: AdminRoomComponent, canActivate: [AuthGuard] },
    { path: 'add-room', component: AdminAddRoomComponent, canActivate: [AuthGuard] }
  ] }
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
