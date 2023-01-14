import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-room',
  templateUrl: './admin-add-room.component.html',
  styleUrls: ['./admin-add-room.component.css']
})
export class AdminAddRoomComponent implements OnInit {
  isAddMode = true;
  id: any;
  form: FormGroup;
  showValidationError: boolean = false;
  hotels: any[] = [];
  roomTypes: any[] = [];

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private route: ActivatedRoute) {
    this.form = this.fb.group({
      roomType: [null, Validators.required],
      hotel: [null, Validators.required],
      number: [null, Validators.required],
      floor: [null]
    });

    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;
  }

  ngOnInit() {
    this.getHotels().subscribe(data => {
      this.hotels = data;
    })
    this.getRoomTypes().subscribe(data => {
      this.roomTypes = data;
    })

    if (!this.isAddMode) {
      this.findRoomById(this.id).subscribe(room => {
        const data = {
          roomType: room.roomType.id,
          hotel: room.hotel.id,
          number: room.roomNumber,
          floor: room.floor
        }
        this.form.patchValue(data)
      });
    }
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

    if(this.isAddMode) {
      this.addRoom(room).subscribe(observer);
    } else {
      this.editRoom(room).subscribe(observer)
    }
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

  editRoom(room: any) {
    return this.http.put<any>(`/api/room/${this.id}`, room);
  }

  findRoomById(id: number) {
    return this.http.get<any>(`/api/room/${id}`);
  }
}
