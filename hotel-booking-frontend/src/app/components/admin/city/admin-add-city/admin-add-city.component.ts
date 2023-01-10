import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-city',
  templateUrl: './admin-add-city.component.html',
  styleUrls: ['./admin-add-city.component.css']
})
export class AdminAddCityComponent implements OnInit {
  isAddMode = true;
  form: FormGroup;
  showValidationError: boolean = false;
  countries: any[] = [];

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.form = this.fb.group({
      name: [null, Validators.required],
      country: [null, Validators.required]
    });
  }

  ngOnInit() {
    this.getCountries().subscribe(data => {
      this.countries = data;
    })
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/admin/city']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };
    
    const city = {
      name: this.form.value.name,
      country: {
        id: +this.form.value.country
      }
    }

    this.addCity(city).subscribe(observer);
    }

  getCountries() {
    return this.http.get<any>('/api/country');
  }

  addCity(country: any) {
    return this.http.post<any>('/api/city', country);
  }
}
