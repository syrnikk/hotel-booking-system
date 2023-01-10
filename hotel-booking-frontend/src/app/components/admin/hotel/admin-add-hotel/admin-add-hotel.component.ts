import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-admin-add-hotel',
  templateUrl: './admin-add-hotel.component.html',
  styleUrls: ['./admin-add-hotel.component.css']
})
export class AdminAddHotelComponent {
  isAddMode = true;
  form: FormGroup;
  showValidationError: boolean = false;
  countries: any[] = [];
  cities: any[] = [];

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private hotelService: HotelService) {
    this.form = this.fb.group({
      name: [null, Validators.required],
      phone: [null],
      title: [null, Validators.required],
      stars: [null, Validators.required],
      description: [null, Validators.required],
      street: [null, Validators.required],
      number: [null, Validators.required],
      postalCode: [null, Validators.required],
      city: [null, Validators.required],
      country: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.hotelService.getCountries().subscribe(data => {
      this.countries = data;
    });
  }

  updateCities(countryName: string) {
    console.log(countryName)
    this.hotelService.getCitiesByCountryName(countryName).subscribe(data => {
      console.log(data)
      this.cities = data;
    });
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/admin/hotel']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };

    const data = this.form.value;

    const hotel = {
      name: data.name,
      address: {
        street: data.street,
        number: data.number,
        postalCode: data.postalCode,
        city: {
          id: +data.city
        }
      },
      phone: data.phone,
      title: data.title,
      description: data.description,
      stars: data.stars
    };

    this.addHotel(hotel).subscribe(observer);
  }

  addHotel(hotel: any) {
    return this.http.post<any>('/api/hotel', hotel);
  }
}
