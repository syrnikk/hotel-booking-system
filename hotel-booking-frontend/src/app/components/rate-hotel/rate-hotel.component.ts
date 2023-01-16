import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-rate-hotel',
  templateUrl: './rate-hotel.component.html',
  styleUrls: ['./rate-hotel.component.css']
})
export class RateHotelComponent implements OnInit {
  rates = [0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]
  form: FormGroup;
  showValidationError: boolean = false;
  id: any;
  currentRate: any;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient, private route: ActivatedRoute) {
    this.form = this.fb.group({
      rate: [null]
    });
    this.id = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/hotel']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };

    const rate = {
      rate: this.form.value.rate,
      hotel: {
        id: this.id
      }
    }
    console.log(rate)
    this.rate(rate).subscribe(observer);
  }

  rate(rate: any) {
    return this.http.post<any>('/api/rate', rate);
  }
}
