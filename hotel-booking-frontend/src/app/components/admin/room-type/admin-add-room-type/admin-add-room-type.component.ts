import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-add-room-type',
  templateUrl: './admin-add-room-type.component.html',
  styleUrls: ['./admin-add-room-type.component.css']
})
export class AdminAddRoomTypeComponent {
  isAddMode = true;
  form: FormGroup;
  showValidationError: boolean = false;

  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.form = this.fb.group({
      type: [null, Validators.required],
      amountOfPeople: [null, Validators.required],
      price: [null, Validators.required],
      description: [null, Validators.required],
    });
  }

  onSubmit() {
    const observer = {
      next: (data: any) => {
        console.log(data);
        this.router.navigate(['/admin/room-type']);
      },
      error: (error: any) => { 
        console.log(error)
        this.showValidationError = true;
      }
    };

    this.addRoomType(this.form.value).subscribe(observer);
    }

  addRoomType(roomType: any) {
    return this.http.post<any>('/api/room-type', roomType);
  }
}
