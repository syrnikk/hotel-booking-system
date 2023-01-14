import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-room',
  templateUrl: './admin-room.component.html',
  styleUrls: ['./admin-room.component.css']
})
export class AdminRoomComponent implements OnInit {
  rooms: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getRooms().subscribe(data => {
      this.rooms = data;
    })
  }

  getRooms() {
    return this.http.get<any>('/api/room');
  }

  deleteRoom(id: string) {
    const room = this.rooms.find((x: any) => x.id === id);
    if (!room) return;
    this.http.delete<any>(`/api/room/${id}`).subscribe(() => this.rooms = this.rooms.filter((x: any) => x.id !== id));
}

}
