import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FindHotelComponent } from './find-hotel/find-hotel.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FindHotelComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
