import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-hotel',
  templateUrl: './admin-hotel.component.html',
  styleUrls: ['./admin-hotel.component.css']
})
export class AdminHotelComponent implements OnInit {
  hotels: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getHotels().subscribe(data => {
      this.hotels = data;
    })
  }

  getHotels() {
    return this.http.get<any>('/api/hotel');
  }

  deleteHotel(id: string) {
    const hotel = this.hotels.find((x: any) => x.id === id);
    if (!hotel) return;
    this.http.delete<any>(`/api/hotel/${id}`).subscribe(() => this.hotels = this.hotels.filter((x: any) => x.id !== id));
}

}
