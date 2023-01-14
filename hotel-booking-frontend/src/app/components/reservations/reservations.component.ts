import { Component, OnInit } from '@angular/core';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent implements OnInit {
  reservations = [];

  constructor(private hotelService: HotelService) {}

  ngOnInit(): void {
    this.hotelService.getReservations().subscribe(data => {
      console.log(data)
      this.reservations = data;
    })
  }
}
