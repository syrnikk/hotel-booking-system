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
    return this.http.get<any[]>(`/api/cities?countryName=${countryName}`)
  }

  getHotelsByCityName(cityName: string) {
    return this.http.get<any>(`/api/hotels?cityName=${cityName}`);
  }

  getAvailableRooms(hotelId: number, startDate: string, endDate: string) {
    return this.http.get<any>(`/api/available-rooms?hotelId=${hotelId}&startDate=${startDate}&endDate=${endDate}`)
  }

  makeBooking(bookingRequest: any) {
    return this.http.post<any>("/api/booking", bookingRequest);
  }

  getReservations() {
    return this.http.get<any>("/api/booking");
  }

  getHotelById(id: number) {
    return this.http.get<any>(`/api/hotel/${id}`);
  }
}
