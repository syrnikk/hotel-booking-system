import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-room',
  templateUrl: './admin-add-room.component.html',
  styleUrls: ['./admin-add-room.component.css']
})
export class AdminAddRoomComponent implements OnInit {
  isAddMode = true;
  form: FormGroup;
  showValidationError: boolean = false;
  hotels: any[] = [];
  roomTypes: any[] = [];

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.form = this.fb.group({
      roomType: [null, Validators.required],
      hotel: [null, Validators.required],
      number: [null, Validators.required],
      floor: [null]
    });
  }

  ngOnInit() {
    this.getHotels().subscribe(data => {
      this.hotels = data;
    })
    this.getRoomTypes().subscribe(data => {
      this.roomTypes = data;
    })
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/admin/room']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };

    const data = this.form.value;
    
    const room = {
      roomType: {
        id: data.roomType
      },
      hotel: {
        id: data.hotel
      },
      roomNumber: data.number,
      floor: data.floor
    }

    this.addRoom(room).subscribe(observer);
    }

  getHotels() {
    return this.http.get<any>('/api/hotel');
  }

  getRoomTypes() {
    return this.http.get<any>('/api/room-type');
  }

  addRoom(room: any) {
    return this.http.post<any>('/api/room', room);
  }
}
