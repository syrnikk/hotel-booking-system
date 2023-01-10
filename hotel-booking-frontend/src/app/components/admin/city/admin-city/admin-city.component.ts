import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-city',
  templateUrl: './admin-city.component.html',
  styleUrls: ['./admin-city.component.css']
})
export class AdminCityComponent implements OnInit {
  cities: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getCities().subscribe(data => {
      this.cities = data;
    })
  }

  getCities() {
    return this.http.get<any>('/api/city');
  }

  deleteCity(id: string) {
    const city = this.cities.find((x: any) => x.id === id);
    if (!city) return;
    this.http.delete<any>(`/api/city/${id}`).subscribe(() => this.cities = this.cities.filter((x: any) => x.id !== id));
}
}
