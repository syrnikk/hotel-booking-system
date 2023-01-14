import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-country',
  templateUrl: './admin-add-country.component.html',
  styleUrls: ['./admin-add-country.component.css']
})
export class AdminAddCountryComponent {
  isAddMode = true;
  id: any;
  form: FormGroup;
  showValidationError: boolean = false;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private route: ActivatedRoute) {
    this.form = this.fb.group({
      name: [null, Validators.required]
    });

    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;

    if (!this.isAddMode) {
      this.findCountryById(this.id).subscribe(country => this.form.patchValue(country));
  }
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

    if(this.isAddMode) {
      this.addCountry().subscribe(observer);
    } else {
      this.editCountry().subscribe(observer)
    }
    
    }
    
  addCountry() {
    return this.http.post<any>('/api/country', this.form.value);
  }

  editCountry() {
    return this.http.put<any>(`/api/country/${this.id}`, this.form.value);
  }

  findCountryById(id: number) {
    return this.http.get<any>(`/api/country/${id}`);
  }
}
