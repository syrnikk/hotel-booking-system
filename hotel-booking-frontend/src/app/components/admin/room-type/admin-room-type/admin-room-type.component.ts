import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-room-type',
  templateUrl: './admin-room-type.component.html',
  styleUrls: ['./admin-room-type.component.css']
})
export class AdminRoomTypeComponent implements OnInit {
  roomTypes: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getRoomTypes().subscribe(data => {
      this.roomTypes = data;
    })
  }

  getRoomTypes() {
    return this.http.get<any>('/api/room-type');
  }

  deleteRoomType(id: string) {
    const roomType = this.roomTypes.find((x: any) => x.id === id);
    if (!roomType) return;
    this.http.delete<any>(`/api/room-type/${id}`).subscribe(() => this.roomTypes = this.roomTypes.filter((x: any) => x.id !== id));
}
}
