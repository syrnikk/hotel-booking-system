import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.css']
})
export class RoomDetailsComponent implements OnInit {
  @Input() room: any;
  @Input() hotel: any;
  rangeList: any = [];
  reservationForm: FormGroup;
  @Input() startDate: any;
  @Input() endDate: any;

  constructor(private fb: FormBuilder, private router: Router, private hotelService: HotelService) {
    this.reservationForm = this.fb.group({
      roomAmount: [null]
    })
  }

  ngOnInit(): void {
    this.rangeList = [...Array(this.room.amountOfAvailableRooms).keys()];
  }

  onSubmit() {
    const bookingRequest = {
      hotelId: this.hotel.id,
      roomTypeId: this.room.id,
      roomAmount: +this.reservationForm.value.roomAmount,
      startDate: this.startDate,
      endDate: this.endDate
    };
    console.log(bookingRequest)
    this.hotelService.makeBooking(bookingRequest).subscribe(data => {
      console.log(data);
    });
    this.router.navigate(['/reservation']);
  }
}
