import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {
  countries: any[] = [];
  cities: any[] = [];
  selectedCountry: any;

  hotels: any[] = [];
  hotelForm: FormGroup;

  constructor(private hotelService: HotelService, private fb: FormBuilder) {
    this.hotelForm = this.fb.group({
      country: [''],
      city: ['']
    })
  }

  ngOnInit(): void {
    this.hotelService.getCountries().subscribe(data => {
      this.countries = data;
    });
  }

  updateCities(countryName: string) {
    this.hotelService.getCitiesByCountryName(this.selectedCountry).subscribe(data => {
      this.cities = data;
    });
  }

  onSubmit() {
    this.hotelService.getHotelsByCityName(this.hotelForm.value.city).subscribe(data => {
      console.log(data)
      this.hotels = data;
    });
  }
}
