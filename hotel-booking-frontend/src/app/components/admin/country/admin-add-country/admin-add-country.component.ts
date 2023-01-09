import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-country',
  templateUrl: './admin-add-country.component.html',
  styleUrls: ['./admin-add-country.component.css']
})
export class AdminAddCountryComponent {
  isAddMode = true;
  form: FormGroup;
  showValidationError: boolean = false;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.form = this.fb.group({
      name: [null, Validators.required]
    });
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/admin/country']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };
    this.addCountry(this.form.value).subscribe(observer);
    }

  addCountry(country: any) {
    return this.http.post<any>('/api/country', country);
  }
}
