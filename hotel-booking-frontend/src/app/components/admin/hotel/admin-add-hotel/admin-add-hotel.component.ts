import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { count } from 'rxjs';
import { HotelService } from 'src/app/services/hotel.service';

@Component({
  selector: 'app-admin-add-hotel',
  templateUrl: './admin-add-hotel.component.html',
  styleUrls: ['./admin-add-hotel.component.css']
})
export class AdminAddHotelComponent {
  isAddMode = true;
  id: any;
  addressId: any;
  form: FormGroup;
  showValidationError: boolean = false;
  countries: any[] = [];
  cities: any[] = [];

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private hotelService: HotelService, private route: ActivatedRoute) {
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

    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;
  }

  ngOnInit(): void {
    this.hotelService.getCountries().subscribe(data => {
      this.countries = data;
    });
    if (!this.isAddMode) {
      this.findHotelById(this.id).subscribe(hotel => {
        this.addressId = hotel.address.id;
        const data = {
          name: hotel.name,
          phone: hotel.phone,
          title: hotel.title,
          stars: hotel.stars,
          description: hotel.description,
          street: hotel.address.street,
          number: hotel.address.number,
          postalCode: hotel.address.postalCode,
          country: hotel.address.city.country.id
        }
        this.form.patchValue(data)
        this.form.patchValue({
          city: hotel.address.city.id 
        })
      });
    }
  }

  updateCities(countryId: any) {
    const country: any = this.countries.find(country => country.id == countryId);
    this.hotelService.getCitiesByCountryName(country.name).subscribe(data => {
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
        id: this.addressId,
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
    console.log(hotel)
    if(this.isAddMode) {
      this.addHotel(hotel).subscribe(observer);
    } else {
      this.editHotel(hotel).subscribe(observer)
    }
  }

  addHotel(hotel: any) {
    return this.http.post<any>('/api/hotel', hotel);
  }

  editHotel(hotel: any) {
    return this.http.put<any>(`/api/hotel/${this.id}`, hotel);
  }

  findHotelById(id: number) {
    return this.http.get<any>(`/api/hotel/${id}`);
  }
}
