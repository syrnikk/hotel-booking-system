import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-city',
  templateUrl: './admin-add-city.component.html',
  styleUrls: ['./admin-add-city.component.css']
})
export class AdminAddCityComponent implements OnInit {
  isAddMode = true;
  id: any;
  form: FormGroup;
  showValidationError: boolean = false;
  countries: any[] = [];

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private route: ActivatedRoute) {
    this.form = this.fb.group({
      name: [null, Validators.required],
      country: [null, Validators.required]
    });

    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;
  }

  ngOnInit() {
    this.getCountries().subscribe(data => {
      this.countries = data;
    })

    if (!this.isAddMode) {
      this.findCityById(this.id).subscribe(city => {
        const data = {
          name: city.name,
          country: city.country.id
        }
        this.form.patchValue(data)
      });
    }
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

    if(this.isAddMode) {
      this.addCity(city).subscribe(observer);
    } else {
      this.editCity(city).subscribe(observer)
    }
  }

  getCountries() {
    return this.http.get<any>('/api/country');
  }

  addCity(city: any) {
    return this.http.post<any>('/api/city', city);
  }

  editCity(city: any) {
    return this.http.put<any>(`/api/city/${this.id}`, city);
  }

  findCityById(id: number) {
    return this.http.get<any>(`/api/city/${id}`);
  }

}
