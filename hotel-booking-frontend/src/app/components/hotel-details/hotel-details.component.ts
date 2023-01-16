import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-hotel-details',
  templateUrl: './hotel-details.component.html',
  styleUrls: ['./hotel-details.component.css']
})
export class HotelDetailsComponent implements OnInit {
  @Input() hotel: any;
  rate: any;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getRate(this.hotel.id).subscribe(data => {
      this.rate = data;
    })
  }

  getRate(hotelId: number) {
    return this.http.get<any>(`/api/rate?hotelId=${hotelId}`);
  }
}
