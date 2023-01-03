import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs';

@Component({
  selector: 'app-room-booking',
  templateUrl: './room-booking.component.html',
  styleUrls: ['./room-booking.component.css']
})
export class RoomBookingComponent {
  roomForm: FormGroup;
  hotel: any;

  constructor(public activatedRoute: ActivatedRoute, private fb: FormBuilder) {
    this.roomForm = this.fb.group({
      startDate: [null],
      endDate: [null],
      numberOfPeople: [null]
    })
  }

  ngOnInit() {
      this.activatedRoute.paramMap.pipe(map(() => window.history.state)).subscribe(data => {
        this.hotel = data;
      });
      console.log(this.hotel);
  }

  onSubmit() {
    
  }
}
