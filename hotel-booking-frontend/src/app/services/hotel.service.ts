import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Country } from '../model/country.model';

@Injectable({
  providedIn: 'root'
})
export class HotelService {

  constructor(private http: HttpClient) { }

  getCountries(): Observable<any[]> {
    return this.http.get<any[]>("/api/country");
  }

  getCitiesByCountryName(countryName: string): Observable<any[]> {
    return this.http.get<any[]>(`/api/city?countryName=${countryName}`)
  }

  getHotelsByCityName(cityName: string) {
    return this.http.get<any>(`/api/hotel?cityName=${cityName}`);
  }

  getAvailableRooms(hotelId: number, startDate: string, endDate: string) {
    return this.http.get<any>(`/api/room?hotelId=${hotelId}&startDate=${startDate}&endDate=${endDate}`)
  }

  makeBooking(bookingRequest: any) {
    return this.http.post<any>("/api/booking", bookingRequest)
  }
}
