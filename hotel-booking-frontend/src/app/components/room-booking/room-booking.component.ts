import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';

@Component({
  selector: 'app-room-booking',
  templateUrl: './room-booking.component.html',
  styleUrls: ['./room-booking.component.css']
})
export class RoomBookingComponent {
  hotel: any;

  constructor(public activatedRoute: ActivatedRoute) {}

  ngOnInit() {
      this.activatedRoute.paramMap.pipe(map(() => window.history.state)).subscribe(data => {
        this.hotel = data;
      });
      console.log(this.hotel);
  }
}
