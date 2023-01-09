import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs';

@Component({
  selector: 'app-admin-country',
  templateUrl: './admin-country.component.html',
  styleUrls: ['./admin-country.component.css']
})
export class AdminCountryComponent implements OnInit {
  countries: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getCountries().subscribe(data => {
      this.countries = data;
    })
  }

  getCountries() {
    return this.http.get<any>('/api/country');
  }

  deleteCountry(id: string) {
    const country = this.countries.find((x: any) => x.id === id);
    if (!country) return;
    this.http.delete<any>(`/api/country/${id}`).subscribe(() => this.countries = this.countries.filter((x: any) => x.id !== id));
}
}
