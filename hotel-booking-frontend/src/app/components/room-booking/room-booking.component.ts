import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-room-booking',
  templateUrl: './room-booking.component.html',
  styleUrls: ['./room-booking.component.css']
})
export class RoomBookingComponent {
  roomForm: FormGroup;
  hotel: any = {};
  availableRooms = []
  startDate: any;
  endDate: any;

  constructor(public activatedRoute: ActivatedRoute, private fb: FormBuilder, private hotelService: HotelService) {
    this.roomForm = this.fb.group({
      startDate: [null],
      endDate: [null],
    })
  }

  ngOnInit() {
      this.activatedRoute.params.subscribe(data => {
        this.hotelService.getHotelById(data['id']).subscribe(hotel => {
          this.hotel = hotel;
        })
      });
  }

  onSubmit() {
    this.hotelService.getAvailableRooms(this.hotel.id, this.roomForm.value.startDate, this.roomForm.value.endDate)
    .subscribe(data => {
      this.availableRooms = data;
      this.startDate = this.roomForm.value.startDate;
      this.endDate = this.roomForm.value.endDate;
      console.log(this.availableRooms)
    })
  }
}
